package com.pictionaryparty.ui.main.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pictionaryparty.ui.components.PrimaryButton
import com.pictionaryparty.ui.components.SecondaryButton

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GroupBottomSheet(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    expand:Boolean,
    sheetContent: @Composable () -> Unit,
    mainContent: @Composable () -> Unit
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
        sheetContent()
    },
        sheetPeekHeight = 0.dp) {
        mainContent()
    }
    if(expand && !bottomSheetScaffoldState.bottomSheetState.hasExpandedState) {
        LaunchedEffect(bottomSheetScaffoldState.bottomSheetState){
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
    }
}