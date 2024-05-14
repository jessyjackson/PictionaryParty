package com.pictionaryparty

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import io.getstream.chat.android.client.BuildConfig
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.livedata.ChatDomain

@HiltAndroidApp
class DrawApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        val logLevel = if (BuildConfig.DEBUG){
            ChatLogLevel.ALL
        }else{
            ChatLogLevel.NOTHING
        }

        val client : ChatClient =
            ChatClient.Builder(getString(R.string.stream_api_key),this)
                .logLevel(logLevel)
                .build()
        ChatDomain.Builder(client, this)
            .offlineEnabled()
            .build()
    }
}