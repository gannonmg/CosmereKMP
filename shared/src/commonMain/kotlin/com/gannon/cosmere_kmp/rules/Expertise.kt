package com.gannon.cosmere_kmp.rules

import com.gannon.cosmere_kmp.rules.items.ItemKind
import kotlinx.serialization.Serializable
import com.gannon.cosmere_kmp.rules.Culture

@Serializable
sealed interface Expertise {
    @Serializable
    data class Cultural(val culture: Culture) : Expertise

    @Serializable
    data class ItemExpertise(val itemRef: ItemReference) : Expertise

    @Serializable
    data class Other(val name: String) : Expertise
}

@Serializable
data class ItemReference(
    val name: String,
    val kind: ItemKind,
)
