package com.pictionaryparty.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    @set: Inject
    lateinit var gameAssistedFactory: GameViewModel.GameAssistedFactory

    private val viewModel by viewModels<GameViewModel>{
        GameViewModel.provideGameAssistedFacory(gameAssistedFactory,intent.getStringExtra(EXTRA_CID)!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRandomWords()
        setContent{
            Text(
                text = "this is game"
            )
        }
    }
    companion object{
        private const val EXTRA_CID = "extra_cid"
        fun start(context : Context, cid: String){
            Intent(context,GameActivity::class.java).also {
                it.putExtra(EXTRA_CID,cid)
                context.startActivity(it)
            }
        }
    }
}