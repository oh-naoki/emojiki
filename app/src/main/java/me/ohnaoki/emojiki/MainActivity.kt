package me.ohnaoki.emojiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            val uiState = viewModel.uiState
            val uiEffect = viewModel.uiEffect

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    EmojiWindow(emoji = uiState.targetEmoji)
                    EmojiGrid(uiState.emojiList) {
                        viewModel.onEmojiTapped(emoji = it)
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
                            viewModel.onCompleteAnimationEnded()
                        }
                        Text(text = "👏", style = TextStyle(fontSize = 48.sp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmojiWindow(emoji: Emoji) {
    Card(
        shape = CircleShape,
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = emoji.emojiText,
            style = TextStyle(fontSize = 64.sp)
        )
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

@Composable
fun EmojiView(emoji: Emoji, onEmojiTapped: (Emoji) -> Unit) {
    Text(text = emoji.emojiText, Modifier.clickable { onEmojiTapped(emoji) })
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

