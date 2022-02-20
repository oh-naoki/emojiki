package me.ohnaoki.emojiki

class StopWatch {

    private val lapTimeList: MutableList<Long> = mutableListOf()
    private var startUnixTime: Long = 0

    fun start() {
        startUnixTime = System.currentTimeMillis()
    }

    fun stop() {
        val lapTime = System.currentTimeMillis() - startUnixTime
        save(lapTime = lapTime)
    }

    fun getTotalLapTimeSec() = lapTimeList.sum() / 1000

    private fun save(lapTime: Long) {
        lapTimeList.add(lapTime)
    }
}