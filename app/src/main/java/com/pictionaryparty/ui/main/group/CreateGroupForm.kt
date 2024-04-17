package com.pictionaryparty.ui.main.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.PrimaryButton

@Composable
fun CreateGroupForm() {
    var displayName = remember {
        mutableStateOf("")
    }
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ){
        val column = createRef()

        Column (
            modifier = Modifier.constrainAs(column) {
                top.linkTo(parent.top, 16.dp)
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end,16.dp)
                width = Dimension.fillToConstraints
            }
        ){
            AppTextField(
                label = stringResource(id = R.string.create_group_name),
                onValueChange = {displayName.value = it},
                value = displayName.value
            )
            PrimaryButton(text = stringResource(id = R.string.create_group),marginTop = 16.dp)

        }
    }

}
@Preview
@Composable
fun DefaultPreview() {
    CreateGroupForm()
}
