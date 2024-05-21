package com.pictionaryparty.game

import GameScreenBottomSheet
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.pictionaryparty.utils.toBitmap
import io.getstream.sketchbook.Sketchbook
import io.getstream.sketchbook.rememberSketchbookController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GameScreen(viewModel: GameViewModel) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
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