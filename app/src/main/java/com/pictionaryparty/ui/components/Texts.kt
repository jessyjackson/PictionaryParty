package com.pictionaryparty.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    text: String,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    Text(
        text = text,
        modifier = modifier
            .clickable { onClick?.invoke() }
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = Color(39,68,92)
    )
}

@Composable
fun MainScreenTitleText(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    text: String,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    Text(
        text = text,
        modifier = modifier
            .clickable { onClick?.invoke() }
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center,
        color = Color(44,53,60)
    )
}

@Composable
fun SubtitleText(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    text: String,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    Text(
        text = text,
        modifier = modifier
            .clickable { onClick?.invoke() }
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center
    )
}

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    text: String,
    size: TextUnit,
    align: TextAlign = TextAlign.Left,
    weight: FontWeight = FontWeight.Normal,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    Text(
        text = text,
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clickable { onClick?.invoke() }
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        fontSize = size,
        fontWeight = weight,
        textAlign = align
    )
}