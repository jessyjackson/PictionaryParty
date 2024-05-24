package com.pictionaryparty.ui.main

import androidx.compose.foundation.background
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pictionaryparty.R
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pictionaryparty.ui.components.DisplayImage
import com.pictionaryparty.ui.components.PrimaryButton
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.components.TitleText
import com.pictionaryparty.ui.components.ThirdButton
import com.pictionaryparty.ui.main.navigation.CREATE_GROUP
import com.pictionaryparty.ui.main.navigation.JOIN_GROUP
import com.pictionaryparty.ui.main.navigation.RULES

@Composable
fun Splash(navController: NavController) {
    val gradientColorList = listOf(
        Color(58,71,82),
        Color(89,69,79),
        Color(149,116,132),
        Color(89,69,79),
        Color(58,71,82)
    )
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        val column = createRef()

        Column(
            modifier = Modifier
                .constrainAs(column) {
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end,16.dp)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.CenterHorizontally // Centro orizzontalmente il contenuto
        ) {
            TitleText(text = "Home")
            DisplayImage(
                painter = painterResource(id = R.drawable.home_image),
                modifier = Modifier
                    .size(350.dp) // Imposta la dimensione dell'immagine, puoi regolare questa dimensione come preferisci
                    .padding(top = 10.dp, bottom = 20.dp), // Aggiungi un padding inferiore per distanziare dall'elemento successivo
                shape = RoundedCornerShape(16.dp) // Specifica il bordo arrotondato
            )
            PrimaryButton(
                text = stringResource(id = R.string.rules),
                marginTop = 10.dp,
                onClick = {
                    navController.navigate(RULES)
                }

            )
            SecondaryButton(
                text = stringResource(id = R.string.create_game),
                marginTop = 16.dp,
                onClick = {
                    navController.navigate(CREATE_GROUP)
                }
            )
            ThirdButton(
                text = stringResource(id = R.string.join_game),
                marginTop = 16.dp,
                onClick = {
                    navController.navigate(JOIN_GROUP)
                }
            )
        }
    }
}

@Composable
fun GradientBackgroundBrush(
    isVerticalGradient: Boolean,
    colors: List<Color>
): Brush {
    val endOffset = if (isVerticalGradient){
        Offset(0f, Float.POSITIVE_INFINITY)
    }else{
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
fun SplashPreview() {
    Splash(rememberNavController())
}
