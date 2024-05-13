package com.pictionaryparty.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun GameScreen(viewModel: GameViewModel)
{
    val isHost by viewModel.isHost.collectAsState()
    val randomWords by viewModel.randomWords.collectAsState()


    if(isHost && randomWords != null)
    {
        WordSelectionDialog(words = randomWords!!)  {selectedWord ->
            viewModel.setSelectedWord(selectedWord)
        }
    }
}