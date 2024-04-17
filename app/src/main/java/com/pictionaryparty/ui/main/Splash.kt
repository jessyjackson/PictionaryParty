package com.pictionaryparty.ui.main

import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pictionaryparty.R
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pictionaryparty.ui.components.PrimaryButton
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.main.navigation.CREATE_GROUP
import com.pictionaryparty.ui.main.navigation.JOIN_GROUP


@Composable
fun Splash(navController: NavController) {
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
            PrimaryButton(
                text = stringResource(id = R.string.create_game),
                marginTop = 16.dp,
                onClick = {
                    navController.navigate(CREATE_GROUP)
                }
            )
            SecondaryButton(
                text = stringResource(id = R.string.join_game),
                marginTop = 16.dp,
                onClick = {
                    navController.navigate(JOIN_GROUP)
                }
            )
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash(rememberNavController())
}