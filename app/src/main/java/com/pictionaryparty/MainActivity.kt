package com.pictionaryparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.pictionaryparty.ui.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }

        var database = Firebase.database;
        val myRef = database.getReference("message");

        myRef.setValue("Hello world!")
    }
}
