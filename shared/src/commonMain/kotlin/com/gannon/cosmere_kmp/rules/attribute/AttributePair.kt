package com.gannon.cosmere_kmp.rules.attribute

import com.gannon.cosmere_kmp.util.EnumDisplayNamedOption

//@Serializable
enum class AttributePair : EnumDisplayNamedOption {
    Physical,
    Cognitive,
    Spiritual;

    /** First attribute in this pair. */
    val first: Attribute
        get() = attributes.first

    /** Second attribute in this pair. */
    val second: Attribute
        get() = attributes.second

    /** Attributes grouped by this pair. */
    val attributes: Pair<Attribute, Attribute>
        get() = when (this) {
            Physical -> Attribute.Strength to Attribute.Speed
            Cognitive -> Attribute.Intellect to Attribute.Willpower
            Spiritual -> Attribute.Awareness to Attribute.Presence
        }
}
