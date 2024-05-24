package com.pictionaryparty.game

import GameScreenBottomSheet
import android.content.Context
import android.content.Intent
import android.window.SplashScreen
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pictionaryparty.MainActivity
import com.pictionaryparty.R
import com.pictionaryparty.ui.main.MainScreen
import com.pictionaryparty.ui.main.Splash
import com.pictionaryparty.utils.toBitmap
import io.getstream.sketchbook.Sketchbook
import io.getstream.sketchbook.rememberSketchbookController
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GameScreen(viewModel: GameViewModel) { //,navController: NavHostController
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var backPressed by remember { mutableStateOf(false) }
    var win by remember {
        mutableStateOf(viewModel.win)
    }
    val context = LocalContext.current

    BackHandler {
        coroutineScope.launch {
            viewModel.resetVariables()
            backPressed = true
        }
    }

    if (backPressed) {
        navigateToMainActivity(context)
    }

    if(win)
    {
        showAlert(context)
    }

    GameScreenBottomSheet(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        sheetContent = { ChatWindow(viewModel) }
    ) {
        GameDrawing(viewModel = viewModel)
    }
}

@Composable
fun GameDrawing(viewModel: GameViewModel) {
    val isHost by viewModel.isHost.collectAsState()
    if (isHost) {
        GameDrawingHost(viewModel = viewModel)
    } else {
        GameDrawingNormal(viewModel = viewModel)
    }
}

@Composable
fun GameDrawingHost(viewModel: GameViewModel) {
    val randomWords by viewModel.randomWords.collectAsState()
    val selectedWord by viewModel.selectedWord.collectAsState()

    if (randomWords != null && selectedWord == null) {
        WordSelectionDialog(words = randomWords!!) { word ->
            viewModel.setSelectedWord(word)
        }
    } else {
        val sketchbookController = rememberSketchbookController()
        sketchbookController.setPaintStrokeWidth(8f)
        sketchbookController.setPaintColor(Color.Black)
        SketchbookScreen(sketchbookController) {
            viewModel.broadcastBitmap(it)
        }
    }
}

@Composable
fun GameDrawingNormal(viewModel: GameViewModel) {
    val drawingImage = viewModel.newDrawingImage.value

    Box(modifier = Modifier.fillMaxSize()) {
        drawingImage?.toBitmap()?.asImageBitmap()?.let {
            Image(bitmap = it, contentDescription = "")
        }
    }
}

fun navigateToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

fun showAlert(context: Context) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setTitle("Congratulations!!")
    dialogBuilder.setMessage("You guessed it!")
    dialogBuilder.setPositiveButton("Back to home") { dialog, _ ->
        dialog.dismiss()
        navigateToMainActivity(context)
    }
    val alertDialog = dialogBuilder.create()
    alertDialog.show()

    val window = alertDialog.window
    window?.setBackgroundDrawableResource(R.drawable.custom_dialog_layout)
    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        ?.setTextColor(ContextCompat.getColor(context, R.color.color_your_custom_color))
}