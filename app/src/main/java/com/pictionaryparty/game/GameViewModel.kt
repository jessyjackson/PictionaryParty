package com.pictionaryparty.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pictionaryparty.data.RandomWords
import com.pictionaryparty.data.RandomWordsApi
import com.pictionaryparty.utils.KEY_HOST_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject



class GameViewModel @AssistedInject constructor(
    //private val firebaseDb = FirebaseDatabase.getInstance().getReference("/Users/carlo/Downloads")
    private val randomWords: RandomWords,
    private val chatClient : ChatClient,
    @Assisted val cid : String
) : ViewModel(){
    private  val _isHost = MutableStateFlow(false)
    val isHost :StateFlow<Boolean>
        get() = _isHost
    init {
        fetchChannelInformation()
    }
    @AssistedFactory
    interface GameAssistedFactory{
        fun create(cid:String) : GameViewModel
    }
    companion object{
        fun provideGameAssistedFacory(factory: GameAssistedFactory,cid : String) : ViewModelProvider.NewInstanceFactory{
            return object :ViewModelProvider.NewInstanceFactory(){
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(cid) as T
                }
            }
        }
    }
    private fun fetchChannelInformation() = viewModelScope.launch{
        var channel = chatClient.channel(cid)
        var result = channel.watch().await()
        result.onSuccess {
            _isHost.value = it.extraData[KEY_HOST_NAME] == chatClient.getCurrentUser()

            if (_isHost.value){
                getRandomWords()
            }
            else{
                //partecipanti normali
            }
        }
    }
    public fun getRandomWords() = viewModelScope.launch {
        val  randomWords = randomWords.getRandomWords()
        println(randomWords)
    }
}