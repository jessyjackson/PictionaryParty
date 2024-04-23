package com.pictionaryparty.ui.main.group

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
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

    if(expand){//&& !bottomSheetScaffoldState.bottomSheetState.hasExpandedState) { // sto if di merda non funziona
        LaunchedEffect(bottomSheetScaffoldState.bottomSheetState){
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
    }
}