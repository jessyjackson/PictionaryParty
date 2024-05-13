package com.pictionaryparty.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pictionaryparty.data.RandomWords
import com.pictionaryparty.utils.KEY_HOST_NAME
import com.pictionaryparty.utils.KEY_NAME
import com.pictionaryparty.utils.KEY_SELECTED_WORD
import com.pictionaryparty.utils.groupName
import com.pictionaryparty.utils.hostName
import com.pictionaryparty.utils.selectedWord
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.channel.subscribeFor
import io.getstream.chat.android.client.events.ChannelUpdatedByUserEvent
import io.getstream.chat.android.client.subscribeFor
import io.getstream.chat.android.client.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GameViewModel @AssistedInject constructor(
    //private val firebaseDb = FirebaseDatabase.getInstance().getReference("/Users/carlo/Downloads")
    private val randomWord: RandomWords,
    private val chatClient : ChatClient,
    @Assisted val cid : String
) : ViewModel(){
    private val channelClient = chatClient.channel(cid)

    private val _isHost = MutableStateFlow(false)
    val isHost :StateFlow<Boolean>
        get() = _isHost

    private val _randomWords = MutableStateFlow<List<String>?>(null)
    val randomWords :StateFlow<List<String>?>
        get() = _randomWords

    private val _selectedWord = MutableStateFlow<String?>(null);
    val selectedWord:StateFlow<String?> = _selectedWord;
    init {
        fetchChannelInformation()
        subscribeToChannelEvents()
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
            if (isHost.value) {
                getRandomWords()
            } else {

            }
        }
    }

    private fun subscribeToChannelEvents()
    {
        channelClient.subscribeFor<ChannelUpdatedByUserEvent>{
            val channel = it.channel
            _selectedWord.value = channel.selectedWord
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
}