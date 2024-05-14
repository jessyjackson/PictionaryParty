package com.pictionaryparty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    onValueChange: ((input: String) -> Unit)? = null,
    value: String = "",
    label: String,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 10.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp
        ),
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(39,68,92),
            unfocusedBorderColor = Color.Black
        ),
        enabled = enabled,
        onValueChange = { onValueChange?.invoke(it) },
        label = {
            val labelColor = if (isFocused) Color(39,68,92) else Color.Black // Determina il colore dell'etichetta in base allo stato del focus
            Text(
                text = label,
                style = TextStyle(
                    color = labelColor,
                    fontSize = 15.sp,
                )
            )
        },
        shape = RoundedCornerShape(3.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Person,
                contentDescription = "Icon")
        },
        maxLines = 1
    )

    /*var focusColor = Color(39,68,92)
    var unfocusColor = Color(191,215,237)


        /*modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),

        value = value,
        enabled = enabled,
        onValueChange = { onValueChange?.invoke(it) },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = unfocusColor,
            focusedIndicatorColor = focusColor,
        )*/
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            /*.background(Color.White)*/
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange?.invoke(it)},
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = unfocusColor,
                    focusedIndicatorColor = focusColor
                ),

                label = {Text(label)},
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "group name"
                        )
                    }
                }
            )
        }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextFieldLimitTime(
    onValueChange: ((input: String) -> Unit)? = null,
    value: String = "",
    label: String,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 10.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = marginTop, bottom = marginBottom, start = marginLeft, end = marginRight),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(39,68,92),
            unfocusedBorderColor = Color(39,68,92)
        ),
        enabled = enabled,
        onValueChange = { onValueChange?.invoke(it) },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    color = Color(39,68,92),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.DateRange,
                contentDescription = "Icon")
        },
        maxLines = 1

    )
}

@Preview
@Composable
fun TextFieldPreview() {
    AppTextField(
        label = "Sample Text Field"
    )
}
