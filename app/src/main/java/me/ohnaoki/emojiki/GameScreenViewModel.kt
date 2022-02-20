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

    private val stopWatch = StopWatch()

    init {
        startLapTime()
    }

    fun onEmojiTapped(emoji: Emoji) {
        if (emoji == uiState.targetEmoji) {
            stopLatTime()
            uiState = uiState.copy(gameCount = uiState.gameCount + 1)
            uiEffect = MainViewEffect.OnCollectAnswer
        }
    }

    fun onCompleteAnimationEnded() {
        uiEffect = null
        if (uiState.gameCount == 2) {
            uiEffect = MainViewEffect.OnAllCompleted(stopWatch.getTotalLapTimeSec())
        } else {
            uiState = uiState.copy(emojiList = emojis.shuffled(), targetEmoji = emojis.random())
            startLapTime()
        }
    }

    private fun startLapTime() {
        stopWatch.start()
    }

    private fun stopLatTime() {
        stopWatch.stop()
    }
}

data class MainUiState(
    val loading: Boolean = false,
    val emojiList: List<Emoji> = emptyList(),
    val targetEmoji: Emoji = emojiList.random(),
    val gameCount: Int = 0
)

sealed class MainViewEffect {
    object OnCollectAnswer : MainViewEffect()
    data class OnAllCompleted(val gameClearTime: Long) : MainViewEffect()
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

