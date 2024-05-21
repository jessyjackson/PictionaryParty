import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GameScreenBottomSheet(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sheetContent: @Composable () -> Unit,
    mainContent: @Composable () -> Unit
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            sheetContent()
        },
        sheetPeekHeight = 56.dp
    ) {
        mainContent()
    }
}