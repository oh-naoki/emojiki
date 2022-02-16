package me.ohnaoki.emojiki

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun GameScreen(viewModel: GameScreenViewModel = viewModel()) {
    val uiState = viewModel.uiState
    val uiEffect = viewModel.uiEffect

    BackHandler(enabled = true) {}

    GameScreenContent(
        uiState = uiState,
        uiEffect = uiEffect,
        onEmojiTapped = viewModel::onEmojiTapped,
        onCompleteAnimation = viewModel::onCompleteAnimationEnded
    )
}

@Composable
fun GameScreenContent(
    uiState: MainUiState,
    uiEffect: MainViewEffect?,
    onEmojiTapped: (Emoji) -> Unit,
    onCompleteAnimation: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            EmojiWindow(emoji = uiState.targetEmoji)
            EmojiGrid(uiState.emojiList) {
                onEmojiTapped(it)
            }
        }
        if (uiEffect is MainViewEffect.OnCollectAnswer) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                LaunchedEffect(null) {
                    delay(1000)
                    onCompleteAnimation()
                }
                Text(text = "👏", style = TextStyle(fontSize = 48.sp))
            }
        }
    }
}


@Composable
fun EmojiWindow(emoji: Emoji) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "🔍 Find this emoji",
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Card(
            shape = CircleShape
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = emoji.emojiText,
                style = TextStyle(fontSize = 64.sp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmojiGrid(emojiList: List<Emoji>, onEmojiTapped: (Emoji) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 20.dp),
            contentPadding = PaddingValues(all = 8.dp)
        ) {
            items(emojiList) {
                EmojiView(emoji = it, onEmojiTapped)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreenContent(
        uiState = MainUiState(emojiList = emojis),
        uiEffect = null,
        onEmojiTapped = {},
        onCompleteAnimation = {}
    )
}

@Composable
fun EmojiView(emoji: Emoji, onEmojiTapped: (Emoji) -> Unit) {
    Text(text = emoji.emojiText, Modifier.clickable { onEmojiTapped(emoji) })
}

@Preview
@Composable
fun PreviewEmojiWindow() {
    EmojiWindow(emoji = Emoji("🐳"))
}

@Preview
@Composable
fun PreviewEmojiGrid() {
    // "\uD83D\uDC21"
    EmojiGrid(emojiList = emojis, onEmojiTapped = {})
}

@Preview
@Composable
fun PreviewEmojiView() {
    EmojiView(Emoji("🦍"), {})
}