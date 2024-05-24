package com.pictionaryparty.ui.main.group

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.PrimaryButton
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.components.SubTitleText
import com.pictionaryparty.ui.main.GradientBackgroundBrush
import com.pictionaryparty.ui.main.MainViewModel

@Composable
fun CreateGroupForm(viewModel: MainViewModel) {
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
        dialogBuilder.setMessage("Enter group name!")
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
    var displayName = remember {
        mutableStateOf("")
    }
    var limitTime = remember {
        mutableIntStateOf(5)
    }
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            )

    ){
        val column = createRef()

        Column (
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 82.dp)
        ){
            SubTitleText(text = "Create Your Group")
            AppTextField(
                label = stringResource(id = R.string.create_group_name),
                onValueChange = {displayName.value = it},
                value = displayName.value
            )
            SecondaryButton(
                text = stringResource(id = R.string.create_group),
                marginTop = 16.dp,
                onClick = {
                    val a = displayName.value
                    if(a != "") {
                        Log.d("TEST", "CreateGroupForm: " + a)
                        viewModel.createGameGroup(displayName.value, limitTime.intValue)
                    }
                    else
                    {
                        showConnectionErrorDialog(context)
                        Log.d("Errore!", "Non connesso!")
                    }
                })

        }
    }

}
/*
@Preview
@Composable
fun DefaultPreview() {
    CreateGroupForm()
}
*/
