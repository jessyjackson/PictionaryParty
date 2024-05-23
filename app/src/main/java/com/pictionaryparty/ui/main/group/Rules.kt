package com.pictionaryparty.ui.main.group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.CardRules
import com.pictionaryparty.ui.components.SecondaryButton
import com.pictionaryparty.ui.main.MainViewModel

@Composable
fun Rules(viewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 82.dp)
        ) {
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

