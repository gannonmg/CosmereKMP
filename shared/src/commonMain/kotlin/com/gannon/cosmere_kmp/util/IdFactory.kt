package com.gannon.cosmerestatsheet_kmp.util

import kotlin.random.Random
import kotlin.time.Clock

object IdFactory {
    fun newId(prefix: String): String {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        val random = Random.nextLong().toString(16)
        return "$prefix-$timestamp-$random"
    }
}
