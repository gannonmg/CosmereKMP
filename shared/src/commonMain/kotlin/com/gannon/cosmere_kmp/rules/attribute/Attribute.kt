package com.gannon.cosmere_kmp.rules.attribute

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption

//@Serializable
enum class Attribute : EnumDisplayNamedOption {
    STRENGTH,
    SPEED,
    INTELLECT,
    WILLPOWER,
    AWARENESS,
    PRESENCE;

    /** Short label used in dense UI and summaries. */
    val shortLabel: String
        get() = when (this) {
            STRENGTH -> "STR"
            SPEED -> "SPD"
            INTELLECT -> "INT"
            WILLPOWER -> "WIL"
            AWARENESS -> "AWA"
            PRESENCE -> "PRE"
        }
}
