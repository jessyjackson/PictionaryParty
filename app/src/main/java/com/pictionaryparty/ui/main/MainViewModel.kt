package com.pictionaryparty.ui.main

import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import com.pictionaryparty.R
import com.pictionaryparty.data.AppPreference
import com.pictionaryparty.utils.CHANNEL_MESSAGING
import com.pictionaryparty.utils.KEY_HOST_NAME
import com.pictionaryparty.utils.KEY_NAME
import com.pictionaryparty.utils.generateUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.models.ConnectionData
import io.getstream.chat.android.models.User
import io.getstream.result.Result
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val chatClient : ChatClient,
    private val prefs : AppPreference
) : ViewModel(){
    private val userId : String
        get() = prefs.userId ?: generateUserId().also { prefs.userId = it }

    private suspend fun connectUser(displayName :String): Result<ConnectionData> {
        if (chatClient.getCurrentUser() != null){
            chatClient.disconnect(false)
        }
        var user = User(
            id = userId,
            extraData = mutableMapOf(
                KEY_NAME to displayName

            )
        )
        var token = chatClient.devToken(userId)
        return chatClient.connectUser(user,token).await()
    }
    private suspend fun createChannel(
        groupId : String,
        displayName: String
    ){
        chatClient.createChannel(
            CHANNEL_MESSAGING,
            groupId,
            listOf(userId),
            extraData = mutableMapOf(
                KEY_NAME to "$displayName' Group",
                KEY_HOST_NAME to displayName
            )
        ).await()
    }
}