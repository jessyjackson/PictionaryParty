package com.pictionaryparty.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val chatClient : ChatClient
) : ViewModel(){

}