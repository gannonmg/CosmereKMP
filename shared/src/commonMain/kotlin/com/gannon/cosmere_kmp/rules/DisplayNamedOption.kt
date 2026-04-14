package com.gannon.cosmere_kmp.rules

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
