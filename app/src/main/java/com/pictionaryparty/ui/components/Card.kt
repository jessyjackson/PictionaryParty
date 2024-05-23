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
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pictionaryparty.ui.theme.DefaultButtonColor
import com.pictionaryparty.ui.theme.PrimaryButtonColor
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
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    border: BorderStroke = CardDefaults.outlinedCardBorder(enabled),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .size(width = 600.dp, height = 300.dp)
            .padding(10.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(PrimaryButtonColor)
                .border(5.dp, SecondaryButtonColor) // Set the border color and thickness
                .padding(20.dp) // Set the padding for the internal margin
        ) {
            Text("1. Accesso: Accedi o crea un gruppo online.\n\n" +
                    "2. Turni: Una persona del gruppo disegna mentre le altre cercano di indovinare.\n" +
                    "\n" +
                    "2. Gioco: L'obiettivo è indovinare il disegno il più velocemente possibile.",
                Modifier.align(Alignment.Center),
                color = Color.LightGray
            )
        }
    }
}

@Composable
private fun GradientBackgroundBrush(
    isVerticalGradient: Boolean,
    colors: List<Color>
): Brush {
    val endOffset = if (isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )
}

@Preview
@Composable
fun CardPreview() {
    CardRules(onClick = { /*TODO*/ }) {}
}
