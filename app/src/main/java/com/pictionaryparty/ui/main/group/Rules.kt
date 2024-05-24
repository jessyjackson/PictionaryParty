package com.pictionaryparty.ui.main.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.CardRules
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.main.MainViewModel
import com.pictionaryparty.ui.components.TitleText
import com.pictionaryparty.ui.main.GradientBackgroundBrush

@Composable
fun Rules(viewModel: MainViewModel) {
    val gradientColorList = listOf(
        Color(58,71,82),
        Color(89,69,79),
        Color(149,116,132),
        Color(89,69,79),
        Color(58,71,82)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            )
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 82.dp)
        ) {
            TitleText(text = "Rules")
            CardRules(onClick = { /*TODO*/ }) {
            }
        }
    }

}

@Preview
@Composable
private fun PreviewJoinGroup()
{
    Rules(hiltViewModel())
}

