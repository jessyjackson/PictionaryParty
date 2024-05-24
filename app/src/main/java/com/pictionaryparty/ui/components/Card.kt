package com.pictionaryparty.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pictionaryparty.ui.theme.SecondaryButtonColor
import com.pictionaryparty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardRules(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    shape: Shape = CardDefaults.outlinedShape,
    colors: CardColors = CardDefaults.outlinedCardColors(),
    border: BorderStroke = CardDefaults.outlinedCardBorder(enabled),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .size(width = 600.dp, height = 400.dp)
            .padding(15.dp)
            .shadow(8.dp, shape),
        color = SecondaryButtonColor,
        shape = shape,
        border = border,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                buildAnnotatedString {
                    pushStyle(SpanStyle(fontStyle = FontStyle.Italic))

                    withStyle(
                        style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, textDecoration = TextDecoration.Underline),
                        block = { append("Access") }
                    )

                    append(": Log in or create a group online.\n\n")

                    withStyle(
                        style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, textDecoration = TextDecoration.Underline),
                        block = { append("Turns") }
                    )

                    append(": One person in the group draws while others try to guess.\n\n")

                    withStyle(
                        style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, textDecoration = TextDecoration.Underline),
                        block = { append("Game") }
                    )

                    append(": The goal is to guess the drawing as quickly as possible.")
                },
                Modifier.align(Alignment.Center),
                color = Color.LightGray,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    CardRules(onClick = { /*TODO*/ }) {}
}
