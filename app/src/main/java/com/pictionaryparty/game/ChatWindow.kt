package com.pictionaryparty.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.pictionaryparty.ui.components.AppTextField
import com.pictionaryparty.ui.components.NormalText
import com.pictionaryparty.R
import com.pictionaryparty.ui.components.AppTextFieldGame
import com.pictionaryparty.ui.components.ButtonGame
import com.pictionaryparty.ui.theme.ThirdButtonColor
import com.pictionaryparty.ui.theme.PrimaryButtonColor

@Composable
fun ChatWindow(
    viewModel: GameViewModel
) {
    val isHost by viewModel.isHost.collectAsState()
    val gameChatList by viewModel.gameChatMessage.collectAsState()
    var guess by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(bottom = 105.dp), // Add padding to avoid overlapping with the text field and button
            content = {
                items(gameChatList) { item ->
                    NormalText(text = "${item.user}: ${item.message}", size = 18.sp)
                }
            }
        )

        if (!isHost) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                val (textField, sendButton) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(textField) {
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(sendButton.start)
                            width = Dimension.fillToConstraints
                        }
                        .padding(5.dp)
                ) {
                    AppTextFieldGame(
                        label = stringResource(id = R.string.guess_a_word),
                        value = guess,
                        marginTop = 0.dp,
                        marginLeft = 10.dp,
                        marginRight = 0.dp,
                        marginBottom = 10.dp,
                        onValueChange = { guess = it })
                }

                ButtonGame(
                    onClick = {
                        viewModel.sendGuessToChannel(guess)
                        guess = ""
                    },
                    modifier = Modifier
                        .constrainAs(sendButton) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                        .padding(
                            horizontal = 20.dp,
                            vertical = 18.dp
                        )
                        .width(52.dp)
                        .height(52.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewChatWindow() {
    ChatWindow(hiltViewModel())
}
