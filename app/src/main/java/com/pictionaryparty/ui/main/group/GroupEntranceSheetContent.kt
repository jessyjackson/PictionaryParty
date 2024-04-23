package com.pictionaryparty.ui.main.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.PrimaryButton
import com.pictionaryparty.utils.groupID
import io.getstream.chat.android.client.models.Channel

@Composable
fun GroupEntranceSheetContent(
    channel: Channel?
) {
    val context = LocalContext.current
    Box(
        Modifier
            .padding(16.dp)
            .heightIn(max = 400.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = channel?.groupID ?: "",
            )
            PrimaryButton(
                text = "game",
                marginTop = 24.dp,
                marginBottom = 24.dp,
                onClick = {

                }
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview1(){
    GroupEntranceSheetContent(null)
}