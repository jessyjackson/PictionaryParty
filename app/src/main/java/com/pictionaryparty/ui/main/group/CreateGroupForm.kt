package com.pictionaryparty.ui.main.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.PrimaryButton

@Composable
fun CreateGroupForm() {
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
                label = "Group Name"
            )
            PrimaryButton(text ="Create Group",marginTop = 16.dp)

        }
    }

}
@Preview
@Composable
fun DefaultPreview() {
    CreateGroupForm()
}
