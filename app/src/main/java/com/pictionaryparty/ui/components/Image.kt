package com.pictionaryparty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pictionaryparty.R

@Composable
fun DisplayImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    shape: Shape? = null // Aggiungi il parametro per il bordo arrotondato
) {
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.then(if (shape != null) Modifier.clip(shape) else Modifier) // Applica il bordo arrotondato se fornito
    )
}


@Preview
@Composable
fun DisplayImagePreview() {
    DisplayImage(painter = painterResource(id = R.drawable.home_image))
}
