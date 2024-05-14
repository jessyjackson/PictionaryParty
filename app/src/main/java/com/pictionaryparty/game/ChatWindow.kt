package com.pictionaryparty.game

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.NormalText

@Composable
fun ChatWindow(viewModel: GameViewModel)
{
    val isHost by viewModel.isHost.collectAsState()
    val gameChatList by viewModel.gameChatMessage.collectAsState()
    var guess by remember{ mutableStateOf("") }

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)) {
        val(textField, sendButton, chatList) = createRefs()

        LazyColumn(content = {
            items(gameChatList) {
                item -> NormalText(text = "${item.user}: ${item.message}", size = 18.sp)
            }
        })

        if (!isHost)
        {
            Box(modifier = Modifier.constrainAs(textField){
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                end.linkTo(sendButton.start)
                width = Dimension.fillToConstraints
            }){
                AppTextField(label = stringResource(id = R.string.guess_a_word), value = guess, onValueChange = { guess = it })
            }

            Button(
                modifier = Modifier
                    .constrainAs(sendButton) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(52.dp)
                    .height(52.dp),
                onClick = {
                    viewModel.sendGuessToChannel(guess)
                    guess = ""
                },
                shape = CircleShape
            ) {
                Icon(painter = painterResource(id = io.getstream.chat.android.compose.R.drawable.stream_compose_ic_send), contentDescription = "")
            }
        }
    }
}

/*
@Preview
@Composable
fun PreviewChatWindow()
{
    ChatWindow()
}
 */