package com.pictionaryparty.ui.main.group

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.pictionaryparty.data.GameConnectionState
import com.pictionaryparty.ui.main.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroup(viewModel: MainViewModel) {
    val gameStatus by viewModel.gameConnectionState.collectAsState()
    val channel by viewModel.connectedChannel.collectAsState(initial = null)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    GroupBottomSheet(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        expand = gameStatus is GameConnectionState.Success,
        sheetContent = {
            GroupEntranceSheetContent(channel)
        },
        mainContent = {
            CreateGroupForm(viewModel)
        }
    )
}
