package com.pictionaryparty.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pictionaryparty.R
import com.pictionaryparty.ui.theme.LightTextColor

// Definizione del font personalizzato
val customFont = FontFamily(
    Font(R.font.my_custom_font, FontWeight.Normal)
)

@Composable
fun TitleText(
    text: String,
    fontSize: Int = 60,
    color: Color = LightTextColor,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = color,
        fontFamily = customFont,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun SubTitleText(
    text: String,
    fontSize: Int = 45,
    color: Color = LightTextColor,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = color,
        fontFamily = customFont,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
}

@Preview
@Composable
fun TitleTextPreview() {
    TitleText(text = "Pictionary Party")
}
