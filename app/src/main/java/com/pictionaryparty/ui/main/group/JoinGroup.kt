package com.pictionaryparty.ui.main.group

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pictionaryparty.R
import com.pictionaryparty.data.GameConnectionState
import com.pictionaryparty.game.GameActivity
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.main.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun JoinGroup(viewModel: MainViewModel = hiltViewModel(), navController: NavHostController) {
    val context = LocalContext.current
    val gameConnection by viewModel.gameConnectionState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var displayName by remember { mutableStateOf("") }
    var groupCode by remember { mutableStateOf("") }
    var buttonClicked by remember { mutableStateOf(false) }

    if (gameConnection is GameConnectionState.Success && buttonClicked) {
        GameActivity.start(context, (gameConnection as GameConnectionState.Success).channel.cid)
        Log.d("Connesso!", "Connesso!")
        buttonClicked = false
    } else if (gameConnection is GameConnectionState.Failure) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Errore!")
        builder.setMessage("Codice non trovato!")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
        Log.d("Errore!", "Non connesso!")
        buttonClicked = false
    }

    BackHandler {
        coroutineScope.launch {
            viewModel.resetVariables()
            navController.popBackStack()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 82.dp)
        ) {
            AppTextField(
                label = stringResource(id = R.string.display_name),
                onValueChange = { displayName = it },
                value = displayName,
                marginTop = 16.dp
            )

            AppTextField(
                label = stringResource(id = R.string.group_code),
                onValueChange = { groupCode = it },
                value = groupCode,
                marginTop = 16.dp
            )

            SecondaryButton(
                text = stringResource(id = R.string.join_game),
                onClick = {
                    viewModel.joinGameGroup(displayName, groupCode)
                    buttonClicked = true
                },
                marginTop = 24.dp
            )
        }
    }
}

/*
@Preview
@Composable
fun PreviewJoinGroup() {
    JoinGroup(viewModel = hiltViewModel(), navController = navController)
}
*/