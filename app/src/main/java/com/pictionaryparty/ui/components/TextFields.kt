package com.pictionaryparty.ui.components

import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
/*import com.pictionaryparty.ui.components.AppDropdownMenu*/
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pictionaryparty.ui.theme.PrimaryButtonColor




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
            color = Color.LightGray,
            fontSize = 18.sp
        ),
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray
        ),
        enabled = enabled,
        onValueChange = { onValueChange?.invoke(it) },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 15.sp,
                )
            )
        },
        shape = RoundedCornerShape(3.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Icon",
                tint =Color.LightGray
            )
        },
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextFieldGame(
    onValueChange: ((input: String) -> Unit)? = null,
    value: String = "",
    label: String,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    marginTop: Dp,
    marginBottom: Dp,
    marginLeft: Dp,
    marginRight: Dp
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
            focusedBorderColor = PrimaryButtonColor,
            unfocusedBorderColor = PrimaryButtonColor,
            cursorColor = Color.Black,
            focusedLabelColor = PrimaryButtonColor,
            unfocusedLabelColor = PrimaryButtonColor,
            disabledTextColor = Color.Black 
        ),
        enabled = enabled,
        onValueChange = { onValueChange?.invoke(it) },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    color = if (enabled) Color.Black else Color.Black, // Cambia il colore dell'etichetta in base allo stato abilitato
                    fontSize = 15.sp,
                )
            )
        },
        shape = RoundedCornerShape(3.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        maxLines = 1
    )
}



/*@OptIn(ExperimentalMaterial3Api::class)
/*@Composable
fun Spinnere(
    items: List<String>, // Lista degli elementi dello spinner
    selectedValue: String, // Valore selezionato dello spinner
    onValueChange: (String) -> Unit, // Callback per il cambio del valore dello spinner
    enabled: Boolean = true,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    marginLeft: Dp = 0.dp,
    marginRight: Dp = 0.dp
) {
    // Aggiunta dello spinner
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AppDropdownMenu(
            items = items,
            label = "Select Item", // Etichetta dello spinner
            selectedValue = selectedValue,
            onValueChange = onValueChange
        )
    }
}*/*/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextFieldSecondary(
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
            color = Color.LightGray,
            fontSize = 18.sp
        ),
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray
        ),
        enabled = enabled,
        onValueChange = { onValueChange?.invoke(it) },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 15.sp,
                )
            )
        },
        shape = RoundedCornerShape(3.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Icon",
                tint =Color.LightGray
            )
        },
        maxLines = 1
    )
}

@Preview
@Composable
fun TextFieldPreview() {
    /*val spinnerItems = listOf("Option 1", "Option 2", "Option 3")
    var selectedSpinnerValue by remember { mutableStateOf(spinnerItems.first()) }
    Spinnere(
        items = spinnerItems,
        selectedValue = selectedSpinnerValue,
        onValueChange = { selectedSpinnerValue = it }
    )*/
}
