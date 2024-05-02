package com.pictionaryparty.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    private val viewModel by viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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