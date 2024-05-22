package com.pictionaryparty.ui.main.group

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.pictionaryparty.data.GameConnectionState
import com.pictionaryparty.ui.main.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroup(viewModel: MainViewModel, navController: NavHostController) {
    val gameStatus by viewModel.gameConnectionState.collectAsState()
    val channel by viewModel.connectedChannel.collectAsState(initial = null)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        coroutineScope.launch {
            viewModel.resetVariables()
            navController.popBackStack()
        }
    }

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