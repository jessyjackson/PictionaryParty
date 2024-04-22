package com.pictionaryparty.ui.main.group

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.pictionaryparty.ui.main.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroup(viewModel: MainViewModel) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    GroupBottomSheet(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        expand = true,
        sheetContent = {
            GroupEntranceSheetContent(null)
        },
        mainContent = {
            CreateGroupForm(viewModel)
        }
    )
}
