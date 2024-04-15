package com.pictionaryparty.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import com.pictionaryparty.ui.components.PrimaryButton
import com.pictionaryparty.ui.components.SecondaryButton


@Composable
fun Splash() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val column = createRef()
        Column(
            modifier = Modifier.constrainAs(column) {
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end,16.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            PrimaryButton(text ="Start Game",marginTop = 16.dp)
            SecondaryButton(text ="Settings",marginTop = 16.dp)
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash()
}