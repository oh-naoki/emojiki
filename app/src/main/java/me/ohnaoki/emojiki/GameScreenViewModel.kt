package me.ohnaoki.emojiki

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameScreenViewModel : ViewModel() {

    var uiState by mutableStateOf(MainUiState(loading = false, emojiList = emojis.shuffled()))
        private set

    var uiEffect by mutableStateOf<MainViewEffect?>(null)
        private set

    fun onEmojiTapped(emoji: Emoji) {
        if (emoji == uiState.targetEmoji) {
            uiEffect = MainViewEffect.OnCollectAnswer
        }
    }

    fun onCompleteAnimationEnded() {
        uiEffect = null
        uiState = uiState.copy(emojiList = emojis.shuffled(), targetEmoji = emojis.random())
    }
}

data class MainUiState(
    val loading: Boolean = false,
    val emojiList: List<Emoji> = emptyList(),
    val targetEmoji: Emoji = emojiList.random()
)

sealed class MainViewEffect {
    object OnCollectAnswer : MainViewEffect()
}

val emojis = listOf(
    "🐤",
    "🐦",
    "🐔",
    "🦤",
    "🕊",
    "🦆",
    "🦅",
    "🪶",
    "🦩",
    "🐥",
    "🐣",
    "🦉",
    "🦜",
    "🦚",
    "🐧",
    "🐓",
    "🦢",
    "🦃",
    "🦡",
    "🦇",
    "🐻",
    "🦫",
    "🦬",
    "🐈",
    "⬛",
    "🐗",
    "🐪",
    "🐈",
    "🐱",
    "🐿",
    "🐄",
    "🐮",
    "🦌",
    "🐕",
    "🐶",
    "🐘",
    "🐑",
    "🦊",
    "🦒",
    "🐐",
    "🦍",
    "🦮",
    "🐹",
    "🦔",
    "🦛",
    "🐎",
    "🐴",
    "🦘",
    "🐨",
    "🐆",
    "🦁",
    "🦙",
    "🦣",
    "🐒",
    "🐵",
    "🐁",
    "🐭",
    "🦧",
    "🦦",
    "🐂",
    "🐼",
    "🐾",
    "🐖",
    "🐷",
    "🐽",
    "🐻",
    "🐩",
    "🐇",
    "🐰",
    "🦝",
    "🐏",
    "🐀",
    "🦏",
    "🐕",
    "🦺",
    "🦨",
    "🦥",
    "🐅",
    "🐯",
    "🐫",
    "🦄",
    "🐃",
    "🐺",
    "🦓",
    "🐳",
    "🐡",
    "🐬",
    "🐟",
    "🐙",
    "🦭",
    "🦈",
    "🐚",
    "🐳",
    "🐠",
    "🐋",
    "🌱",
    "🌵",
    "🌳",
    "🌲",
    "🍂",
    "🍀",
    "🌿",
    "🍃",
    "🍁",
    "🌴",
    "🪴",
    "🌱",
    "☘",
    "🌾",
    "🐊",
    "🐊",
    "🐉",
    "🐲",
    "🦎",
    "🦕",
    "🐍",
    "🦖",
    "🐢"
).map { Emoji(it) }

