package com.pictionaryparty.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    @set: Inject
    lateinit var gameAssistedFactory: GameViewModel.GameAssistedFactory

    private val viewModel by viewModels<GameViewModel>{
        GameViewModel.provideGameAssistedFactory(gameAssistedFactory,intent.getStringExtra(EXTRA_CID)!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            GameScreen(viewModel = viewModel)
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