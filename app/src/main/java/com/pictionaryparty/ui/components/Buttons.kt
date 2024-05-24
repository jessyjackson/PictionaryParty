package com.pictionaryparty.ui.components
import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.Image
import  androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import  androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pictionaryparty.R

import com.pictionaryparty.ui.theme.DefaultButtonColor
import com.pictionaryparty.ui.theme.DefaultTextColor
import com.pictionaryparty.ui.theme.LightTextColor
import com.pictionaryparty.ui.theme.PrimaryButtonColor
import com.pictionaryparty.ui.theme.SecondaryButtonColor
import com.pictionaryparty.ui.theme.ThirdButtonColor

@Composable
fun PrimaryButton(
    onClick: (() -> Unit)? = null,
    text: String,
    enabled: Boolean = true,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {

    AppButton(
        onClick = onClick,
        text = text,
        enabled = enabled,
        backgroundColor = PrimaryButtonColor,
        textColor = LightTextColor,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight

    )
}

@Composable
fun SecondaryButton(
    onClick: (() -> Unit)? = null,
    text: String,
    enabled: Boolean = true,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    AppButton(
        onClick = onClick,
        text = text,
        enabled = enabled,
        backgroundColor = SecondaryButtonColor,
        textColor = LightTextColor,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight
    )
}

@Composable
fun ThirdButton(
    onClick: (() -> Unit)? = null,
    text: String,
    enabled: Boolean = true,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    AppButton(
        onClick = onClick,
        text = text,
        enabled = enabled,
        backgroundColor = ThirdButtonColor,
        textColor = LightTextColor,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight
    )
}

@Composable
fun FourthButton(
    onClick: (() -> Unit)? = null,
    text: String,
    enabled: Boolean = true,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    AppButton(
        onClick = onClick,
        text = text,
        enabled = enabled,
        backgroundColor = Color(89, 69, 79),
        textColor = LightTextColor,
        marginTop = marginTop,
        marginBottom = marginBottom,
        marginLeft = marginLeft,
        marginRight = marginRight
    )
}

@Composable
fun ButtonGame(
    onClick: (() -> Unit)? = null,
    text: String? = null,
    enabled: Boolean = true,
    backgroundColor: Color = PrimaryButtonColor,
    textColor: Color = Color.White,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor),
    image : Boolean
) {
    Button(
        onClick = { onClick?.invoke() },
        modifier = modifier
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        enabled = enabled,
        colors = colors,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        if (text != null) {
            Text(
                text = text,
                color = textColor,
                fontFamily = FontFamily.SansSerif,
                fontSize = 150.sp
            )
        }
        if(image){
            Image(painter = painterResource(id = R.drawable.sample_image ), contentDescription = "",
                modifier = Modifier
                    .size(25.dp))
        }
    }
}





@Composable
private fun AppButton(
    onClick: (() -> Unit)? = null,
    text: String,
    enabled: Boolean = true,
    backgroundColor: Color = PrimaryButtonColor,
    textColor: Color = PrimaryButtonColor,
    marginTop: Dp,
    marginBottom: Dp ,
    marginLeft: Dp,
    marginRight: Dp,
) {
    Button(
        onClick = { onClick?.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        enabled = enabled,
        contentPadding = PaddingValues(vertical = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )


    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = FontFamily.SansSerif,
            fontSize = 22.sp
        )
    }
}

@Preview
@Composable
fun ButtonPreview() {
    PrimaryButton(text = "Sample Button")
    //SecondaryButton(text = "Sample Button")
}