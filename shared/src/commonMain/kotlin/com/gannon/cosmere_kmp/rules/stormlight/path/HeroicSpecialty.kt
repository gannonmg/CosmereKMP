package com.gannon.cosmere_kmp.rules.stormlight.path

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption

//import kotlinx.serialization.Serializable

//@Serializable
enum class HeroicSpecialty : EnumDisplayNamedOption {
    INVESTIGATOR,
    SPY,
    THIEF,
    DIPLOMAT,
    FAITHFUL,
    MENTOR,
    ARCHER,
    ASSASSIN,
    TRACKER,
    CHAMPION,
    OFFICER,
    POLITICO,
    ARTIFABRIAN,
    STRATEGIST,
    SURGEON,
    DUELIST,
    SHARDBEARER,
    SOLDIER;

    /** Heroic path associated with this specialty. */
    val path: StormlightHeroicPath
        get() = when (this) {
            INVESTIGATOR, SPY, THIEF -> StormlightHeroicPath.AGENT
            DIPLOMAT, FAITHFUL, MENTOR -> StormlightHeroicPath.ENVOY
            ARCHER, ASSASSIN, TRACKER -> StormlightHeroicPath.HUNTER
            CHAMPION, OFFICER, POLITICO -> StormlightHeroicPath.LEADER
            ARTIFABRIAN, STRATEGIST, SURGEON -> StormlightHeroicPath.SCHOLAR
            DUELIST, SHARDBEARER, SOLDIER -> StormlightHeroicPath.WARRIOR
        }
}
