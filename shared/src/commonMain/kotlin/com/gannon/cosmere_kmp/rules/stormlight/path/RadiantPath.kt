package com.gannon.cosmere_kmp.rules.stormlight.path

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption

//import kotlinx.serialization.Serializable

//@Serializable
enum class RadiantPath : EnumDisplayNamedOption {
    WINDRUNNER,
    SKYBREAKER,
    DUSTBRINGER,
    EDGEDANCER,
    TRUTHWATCHER,
    LIGHTWEAVER,
    ELSECALLER,
    WILLSHAPER,
    STONEWARD;

    /** Spren associated with this Radiant path. */
    val spren: RadiantSpren
        get() = when (this) {
            WINDRUNNER -> RadiantSpren.HONORSPREN
            SKYBREAKER -> RadiantSpren.HIGHSPREN
            DUSTBRINGER -> RadiantSpren.ASHSPREN
            EDGEDANCER -> RadiantSpren.CULTIVATIONSPREN
            TRUTHWATCHER -> RadiantSpren.MISTSPREN
            LIGHTWEAVER -> RadiantSpren.CRYPTIC
            ELSECALLER -> RadiantSpren.INKSPREN
            WILLSHAPER -> RadiantSpren.LIGHTSPREN
            STONEWARD -> RadiantSpren.PEAKSPREN
        }

    /** Surges granted by this Radiant path. */
    val surges: Pair<Surge, Surge>
        get() = when (this) {
            WINDRUNNER -> Surge.ADHESION to Surge.GRAVITATION
            SKYBREAKER -> Surge.GRAVITATION to Surge.DIVISION
            DUSTBRINGER -> Surge.ABRASION to Surge.DIVISION
            EDGEDANCER -> Surge.ABRASION to Surge.PROGRESSION
            TRUTHWATCHER -> Surge.PROGRESSION to Surge.ILLUMINATION
            LIGHTWEAVER -> Surge.ILLUMINATION to Surge.TRANSFORMATION
            ELSECALLER -> Surge.TRANSFORMATION to Surge.TRANSPORTATION
            WILLSHAPER -> Surge.TRANSPORTATION to Surge.COHESION
            STONEWARD -> Surge.COHESION to Surge.TENSION
        }
}
