package com.pictionaryparty.data

import io.getstream.chat.android.client.errors.ChatError
import io.getstream.chat.android.models.Channel


sealed class GameConnectionState {
    object None : GameConnectionState()
    object Loading : GameConnectionState()

    data class Success(val channel : Channel) : GameConnectionState()
    data class Failure(val chatError: ChatError) : GameConnectionState()

}