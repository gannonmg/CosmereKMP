package com.gannon.cosmere_kmp.rules.stormlight

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption
import com.gannon.cosmere_kmp.rules.talent.TalentResourceSource
//import kotlinx.serialization.Serializable

/** Stormlight ancestry options available during character creation. */
//@Serializable
enum class StormlightAncestry : EnumDisplayNamedOption, TalentResourceSource {
    HUMAN,
    SINGER;

    /** Ancestry talent JSON folder for this ancestry. */
    override val resourceDirectory: String
        get() = "ancestry"
}
