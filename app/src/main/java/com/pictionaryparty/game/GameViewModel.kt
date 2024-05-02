package com.pictionaryparty.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pictionaryparty.data.RandomWords
import com.pictionaryparty.data.RandomWordsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val randomWords: RandomWords
) : ViewModel(){
    init {

    }
    private fun fetchChannelInformation(){

    }
    private fun getRandomWords() = viewModelScope.launch {
        val  randomWords = randomWords.getRandomWords()
        println(randomWords)
    }
}