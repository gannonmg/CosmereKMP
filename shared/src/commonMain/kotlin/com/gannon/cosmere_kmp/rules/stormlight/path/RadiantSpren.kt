package com.gannon.cosmere_kmp.rules.stormlight.path

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption
import com.gannon.cosmere_kmp.rules.talent.TalentResourceSource
//import kotlinx.serialization.Serializable

//@Serializable
enum class RadiantSpren : EnumDisplayNamedOption, TalentResourceSource {
    HONORSPREN,
    HIGHSPREN,
    ASHSPREN,
    CULTIVATIONSPREN,
    MISTSPREN,
    CRYPTIC,
    INKSPREN,
    LIGHTSPREN,
    PEAKSPREN;

    /** Radiant spren talent JSON folder for this spren. */
    override val resourceDirectory: String
        get() = "radiant/spren"
}
