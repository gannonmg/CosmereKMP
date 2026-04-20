package com.gannon.cosmere_kmp.rules

import com.gannon.cosmere_kmp.util.EnumDisplayNamedOption
import kotlinx.serialization.Serializable

@Serializable
enum class WHSetting: EnumDisplayNamedOption {
    Stormlight, Mistborn
}
