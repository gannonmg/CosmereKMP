package com.gannon.cosmere_kmp.rules.stormlight.path

import com.gannon.cosmere_kmp.util.EnumDisplayNamedOption
import com.gannon.cosmere_kmp.rules.talent.TalentResourceSource
//import kotlinx.serialization.Serializable

//@Serializable
enum class Surge : EnumDisplayNamedOption, TalentResourceSource {
    ADHESION,
    GRAVITATION,
    DIVISION,
    ABRASION,
    PROGRESSION,
    ILLUMINATION,
    TRANSFORMATION,
    TRANSPORTATION,
    COHESION,
    TENSION;

    /** Radiant surge talent JSON folder for this surge. */
    override val resourceDirectory: String
        get() = "radiant/surge"
}
