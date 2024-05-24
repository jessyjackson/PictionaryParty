package com.pictionaryparty.ui.main.group

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pictionaryparty.R
import com.pictionaryparty.data.GameConnectionState
import com.pictionaryparty.game.GameActivity
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.AppTextFieldSecondary
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.components.SubTitleText
import com.pictionaryparty.ui.main.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun JoinGroup(viewModel: MainViewModel = hiltViewModel(), navController: NavHostController) {
    val gradientColorList = listOf(
        Color(58,71,82),
        Color(89,69,79),
        Color(149,116,132),
        Color(89,69,79),
        Color(58,71,82)
    )

    fun showConnectionErrorDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Error!")
        dialogBuilder.setMessage("Enter all fields!")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        //Imposta il colore di sfondo dell'AlertDialog
        val window = alertDialog.window
        window?.setBackgroundDrawableResource(R.drawable.custom_dialog_layout)
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(context, R.color.color_your_custom_color))
    }

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
    } else if (gameConnection is GameConnectionState.Failure && buttonClicked) {
        /*val builder = AlertDialog.Builder(context)
        builder.setTitle("Errore!")
        builder.setMessage("Codice non trovato!")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()*/
        showConnectionErrorDialog(context)
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
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = com.pictionaryparty.ui.main.GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 82.dp)

        ) {
            SubTitleText(text = "Join a Group")
            AppTextField(
                label = stringResource(id = R.string.display_name),
                onValueChange = { displayName = it },
                value = displayName,
                marginTop = 16.dp
            )

            AppTextFieldSecondary(
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
