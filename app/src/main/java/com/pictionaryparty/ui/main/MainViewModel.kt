package com.pictionaryparty.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pictionaryparty.data.AppPreference
import com.pictionaryparty.data.GameConnectionState
import com.pictionaryparty.utils.CHANNEL_MESSAGING
import com.pictionaryparty.utils.KEY_HOST_NAME
import com.pictionaryparty.utils.KEY_LIMIT_TIME
import com.pictionaryparty.utils.KEY_NAME
import com.pictionaryparty.utils.generateUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.ConnectionData
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val chatClient : ChatClient,
    private val prefs : AppPreference
) : ViewModel(){
    private val userId : String
        get() = prefs.userId ?: generateUserId().also { prefs.userId = it }
    private val _gameConnectionState = MutableStateFlow<GameConnectionState>(GameConnectionState.None)
    val gameConnectionState : StateFlow<GameConnectionState> get() = _gameConnectionState
    private suspend fun connectUser(displayName :String,limitTime: Int): Result<ConnectionData> {
        if (chatClient.getCurrentUser() != null){
            chatClient.disconnect()
        }
        var user = User(
            id = userId,
            extraData = mutableMapOf(
                KEY_NAME to displayName,
                KEY_LIMIT_TIME to limitTime

            )
        )
        var token = chatClient.devToken(userId)
        return chatClient.connectUser(user,token).await()
    }
    private suspend fun createChannel(
        groupId : String,
        displayName: String,
        limitTime: Int
    ): Result<Channel> {
        return chatClient.createChannel(
            CHANNEL_MESSAGING,
            groupId,
            listOf(userId),
            extraData = mutableMapOf(
                KEY_NAME to "$displayName' Group",
                KEY_HOST_NAME to displayName,
                KEY_LIMIT_TIME to limitTime
            )
        ).await()
    }
    fun createGameGroup(displayName: String,limitTime: Int) = viewModelScope.launch{
        val connection = connectUser(displayName,limitTime)
        if (connection.isSuccess) {
            _gameConnectionState.emit(GameConnectionState.Loading)
            val groupId = generateUserId()
            val channel = createChannel(groupId, displayName,limitTime)
            if (channel.isSuccess){
                _gameConnectionState.emit(GameConnectionState.Success(channel.data()))
            }
            else{
                _gameConnectionState.emit(GameConnectionState.Failure(channel.error()))
            }
        }
    }
}