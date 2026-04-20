package com.gannon.cosmere_kmp.rules.attribute

import com.gannon.cosmere_kmp.rules.skill.Skill
import com.gannon.cosmere_kmp.util.EnumDisplayNamedOption
import kotlinx.serialization.Serializable

@Serializable
enum class Attribute : EnumDisplayNamedOption {
    Strength,
    Speed,
    Intellect,
    Willpower,
    Awareness,
    Presence;

    /** Short label used in dense UI and summaries. */
    val shortLabel: String
        get() = when (this) {
            Strength -> "STR"
            Speed -> "SPD"
            Intellect -> "INT"
            Willpower -> "WIL"
            Awareness -> "AWA"
            Presence -> "PRE"
        }

    fun associatedSkills(includeSurges: Boolean = false): List<Skill> =
        Skill.skills(forAttribute = this, includeSurges = includeSurges)
}
