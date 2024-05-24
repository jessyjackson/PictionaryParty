package com.pictionaryparty.game

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.appcompat.app.AlertDialog

import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pictionaryparty.MainActivity
import com.pictionaryparty.R
import com.pictionaryparty.data.GameChatMessage
import com.pictionaryparty.data.RandomWords
import com.pictionaryparty.utils.KEY_HOST_NAME
import com.pictionaryparty.utils.KEY_NAME
import com.pictionaryparty.utils.KEY_SELECTED_WORD
import com.pictionaryparty.utils.groupName
import com.pictionaryparty.utils.groupID
import com.pictionaryparty.utils.selectedWord
import com.pictionaryparty.utils.toBase64String
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.channel.ChannelClient
import io.getstream.chat.android.client.channel.subscribeFor
import io.getstream.chat.android.client.events.ChannelUpdatedByUserEvent
import io.getstream.chat.android.client.events.NewMessageEvent
import io.getstream.chat.android.client.models.Message
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.subscribeFor
import io.getstream.chat.android.client.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class GameViewModel @AssistedInject constructor(
    //private val firebaseDb = FirebaseDatabase.getInstance().getReference("/Users/carlo/Downloads")
    private val randomWord: RandomWords,
    private val chatClient : ChatClient,
    @Assisted val cid : String
) : ViewModel(){
    public var win = mutableStateOf(false)

    private val channelClient = chatClient.channel(cid)

    private val firebaseDb = FirebaseDatabase.getInstance().getReference(channelClient.groupID)

    private val _isHost = MutableStateFlow(false)
    val isHost :StateFlow<Boolean>
        get() = _isHost

    private val _playerName = MutableStateFlow("")
    val playerName :StateFlow<String>
        get() = _playerName

    private val _randomWords = MutableStateFlow<List<String>?>(null)
    val randomWords :StateFlow<List<String>?>
        get() = _randomWords

    private val _gameChatMessages = MutableStateFlow<List<GameChatMessage>>(listOf())
    val gameChatMessage :StateFlow<List<GameChatMessage>>
        get() = _gameChatMessages

    private val _selectedWord = MutableStateFlow<String?>(null);
    val selectedWord:StateFlow<String?> = _selectedWord;

    private val _newDrawingImage: MutableState<String?> = mutableStateOf(null)
    val newDrawingImage: State<String?> = _newDrawingImage

    public var winner = ""

    init {
        fetchChannelInformation()
        subscribeToChannelEvents()
        subscribeToNewMessageEvent()
    }
    @AssistedFactory
    interface GameAssistedFactory{
        fun create(cid:String) : GameViewModel
    }
    companion object{
        fun provideGameAssistedFactory(factory: GameAssistedFactory, cid : String) : ViewModelProvider.NewInstanceFactory{
            return object :ViewModelProvider.NewInstanceFactory(){
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(cid) as T
                }
            }
        }
    }
    private fun fetchChannelInformation() = viewModelScope.launch {
        val result = channelClient.watch().await()
        result.onSuccess {
            _isHost.value = it.createdBy.id == chatClient.getCurrentUser()?.id
            _playerName.value = chatClient.getCurrentUser()?.id.toString()
            Log.i("name",_playerName.value)
            if (isHost.value) {
                getRandomWords()
            } else {
                subscribeToFirebaseDb()
            }
        }
    }

    private fun subscribeToFirebaseDb() {
        firebaseDb.addValueEventListener(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    val stringBitmap = snapshot.getValue(String::class.java)
                    _newDrawingImage.value = stringBitmap
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun broadcastBitmap(bitmap: Bitmap) = viewModelScope.launch {
        val stringBitmap = bitmap.toBase64String()
        firebaseDb.setValue(stringBitmap)
    }

    private fun subscribeToChannelEvents()
    {
        channelClient.subscribeFor<ChannelUpdatedByUserEvent>{
            val channel = it.channel
            _selectedWord.value = channel.selectedWord
        }
    }

    private fun subscribeToNewMessageEvent()
    {
        channelClient.subscribeFor<NewMessageEvent>{
            _gameChatMessages.value = _gameChatMessages.value + GameChatMessage(
                it.user.extraData.get("key_name").toString(),
                it.message.text,
            )
            if(it.message.text.lowercase() == selectedWord.value?.lowercase())
            {
                winner = it.user.id
                win.value = true
                finishGame(it.user)
            }
        }
    }

    private fun finishGame(user: User) = viewModelScope.launch {
        if(_isHost.value)
        {
            channelClient.sendMessage(
                Message(
                    text = "Congratulation! ${user.extraData.get("key_name").toString()} has correct the answer. \uD83C\uDF89",
                    type = "system"
                )
            ).await()
        }
    }

    private fun getRandomWords() = viewModelScope.launch {
        val randomWords = randomWord.getRandomWords()
        _randomWords.emit(randomWords)
    }

     fun setSelectedWord(selectedWord: String) = viewModelScope.launch {
        val hostName = chatClient.getCurrentUser()?.name ?: return@launch

        channelClient.update(
            extraData = mutableMapOf(
                KEY_SELECTED_WORD to selectedWord,
                KEY_NAME to hostName.groupName,
                KEY_HOST_NAME to hostName
            )
        ).await()
    }

    fun sendGuessToChannel(guess: String) = viewModelScope.launch {
        channelClient.sendMessage(
            Message(user = chatClient.getCurrentUser()!!, text = guess)
        ).await()
    }

    fun resetVariables()
    {
        chatClient.disconnect()
        win.value = false
        winner = ""
        _isHost.value = false
        _playerName.value = ""
    }
}