package com.gannon.cosmere_kmp.rules.attribute

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption

//@Serializable
enum class AttributePair : EnumDisplayNamedOption {
    PHYSICAL,
    COGNITIVE,
    SPIRITUAL;

    /** First attribute in this pair. */
    val first: Attribute
        get() = attributes.first

    /** Second attribute in this pair. */
    val second: Attribute
        get() = attributes.second

    /** Attributes grouped by this pair. */
    val attributes: Pair<Attribute, Attribute>
        get() = when (this) {
            PHYSICAL -> Attribute.STRENGTH to Attribute.SPEED
            COGNITIVE -> Attribute.INTELLECT to Attribute.WILLPOWER
            SPIRITUAL -> Attribute.AWARENESS to Attribute.PRESENCE
        }
}
