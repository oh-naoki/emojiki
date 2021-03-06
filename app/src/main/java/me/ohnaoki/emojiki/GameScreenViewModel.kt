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
        if (uiState.gameCount == 5) {
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
    "ðĪ",
    "ðĶ",
    "ð",
    "ðĶĪ",
    "ð",
    "ðĶ",
    "ðĶ",
    "ðŠķ",
    "ðĶĐ",
    "ðĨ",
    "ðĶ",
    "ðĶ",
    "ðĶ",
    "ð§",
    "ð",
    "ðĶĒ",
    "ðĶ",
    "ðĶĄ",
    "ðĶ",
    "ðŧ",
    "ðĶŦ",
    "ðĶŽ",
    "ð",
    "âŽ",
    "ð",
    "ðŠ",
    "ð",
    "ðą",
    "ðŋ",
    "ð",
    "ðŪ",
    "ðĶ",
    "ð",
    "ðķ",
    "ð",
    "ð",
    "ðĶ",
    "ðĶ",
    "ð",
    "ðĶ",
    "ðĶŪ",
    "ðđ",
    "ðĶ",
    "ðĶ",
    "ð",
    "ðī",
    "ðĶ",
    "ðĻ",
    "ð",
    "ðĶ",
    "ðĶ",
    "ðĶĢ",
    "ð",
    "ðĩ",
    "ð",
    "ð­",
    "ðĶ§",
    "ðĶĶ",
    "ð",
    "ðž",
    "ðū",
    "ð",
    "ð·",
    "ð―",
    "ðŧ",
    "ðĐ",
    "ð",
    "ð°",
    "ðĶ",
    "ð",
    "ð",
    "ðĶ",
    "ð",
    "ðĶš",
    "ðĶĻ",
    "ðĶĨ",
    "ð",
    "ðŊ",
    "ðŦ",
    "ðĶ",
    "ð",
    "ðš",
    "ðĶ",
    "ðģ",
    "ðĄ",
    "ðŽ",
    "ð",
    "ð",
    "ðĶ­",
    "ðĶ",
    "ð",
    "ðģ",
    "ð ",
    "ð",
    "ðą",
    "ðĩ",
    "ðģ",
    "ðē",
    "ð",
    "ð",
    "ðŋ",
    "ð",
    "ð",
    "ðī",
    "ðŠī",
    "ðą",
    "â",
    "ðū",
    "ð",
    "ð",
    "ð",
    "ðē",
    "ðĶ",
    "ðĶ",
    "ð",
    "ðĶ",
    "ðĒ"
).map { Emoji(it) }

