package com.pictionaryparty.ui.main.group

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroup() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    GroupBottomSheet(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        expand = true,
        sheetContent = {
            GroupEntranceSheetContent()
        },
        mainContent = {
            CreateGroupForm()
        }
    )
}
