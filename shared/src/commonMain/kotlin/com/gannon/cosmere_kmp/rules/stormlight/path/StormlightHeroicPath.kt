package com.gannon.cosmere_kmp.rules.stormlight.path

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption
import com.gannon.cosmere_kmp.rules.skill.Skill
import com.gannon.cosmere_kmp.rules.talent.TalentResourceSource
//import kotlinx.serialization.Serializable

//@Serializable
enum class StormlightHeroicPath : EnumDisplayNamedOption, TalentResourceSource {
    AGENT,
    ENVOY,
    HUNTER,
    LEADER,
    SCHOLAR,
    WARRIOR;

    /** Heroic talent JSON folder for this path. */
    override val resourceDirectory: String
        get() = "heroic"

    /** Supported specialties for this heroic path. */
    val specialties: List<HeroicSpecialty>
        get() = when (this) {
            AGENT -> listOf(HeroicSpecialty.INVESTIGATOR, HeroicSpecialty.SPY, HeroicSpecialty.THIEF)
            ENVOY -> listOf(HeroicSpecialty.DIPLOMAT, HeroicSpecialty.FAITHFUL, HeroicSpecialty.MENTOR)
            HUNTER -> listOf(HeroicSpecialty.ARCHER, HeroicSpecialty.ASSASSIN, HeroicSpecialty.TRACKER)
            LEADER -> listOf(HeroicSpecialty.CHAMPION, HeroicSpecialty.OFFICER, HeroicSpecialty.POLITICO)
            SCHOLAR -> listOf(HeroicSpecialty.ARTIFABRIAN, HeroicSpecialty.STRATEGIST, HeroicSpecialty.SURGEON)
            WARRIOR -> listOf(HeroicSpecialty.DUELIST, HeroicSpecialty.SHARDBEARER, HeroicSpecialty.SOLDIER)
        }

    /** Starting skill rank granted by this heroic path. */
    val startingSkill: Skill
        get() = when (this) {
            AGENT -> Skill.INSIGHT
            ENVOY -> Skill.DISCIPLINE
            HUNTER -> Skill.PERCEPTION
            LEADER -> Skill.LEADERSHIP
            SCHOLAR -> Skill.LORE
            WARRIOR -> Skill.ATHLETICS
        }
}
