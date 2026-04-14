package com.gannon.cosmere_kmp.rules.skill

enum class SkillRankState {
    /** The player does not possess this rank of the skill. */
    Empty,
    /** The player has permanently gained this rank of the skill. */
    Claimed,
    /**
     * The player has gained this rank of the skill from some source other than level up selection.
     * This may be higher than the rank allowed at a tier.
     * This does not count towards meeting the prerequisite.
     */
    Granted,
    /** This rank of the skill is not available at this Tier of play. */
    Ineligible,
    /** The player may select this rank during level up. */
    Available,
    /** The player has selected this during level up. */
    Selected
}