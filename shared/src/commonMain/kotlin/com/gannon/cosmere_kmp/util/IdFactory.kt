package com.gannon.cosmere_kmp.util

import kotlin.random.Random
import kotlin.time.Clock

object IdFactory {
    fun newId(prefix: String): String {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        val random = Random.nextLong().toString(16)
        return "$prefix-$timestamp-$random"
    }
}

/** UI-facing option with a stable identifier and display name. */
interface DisplayNamedOption {
    val id: String
    val displayName: String
}

/** Enum-backed option that derives id and display name from its enum name. */
interface EnumDisplayNamedOption : DisplayNamedOption {
    val name: String

    override val id: String
        get() = name

    override val displayName: String
        get() = name.lowercase().replaceFirstChar { it.uppercase() }
}