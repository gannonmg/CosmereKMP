package com.gannon.cosmere_kmp.rules.ruleTables

enum class PlayTier(
    val value: Int,
    val minLevel: Int,
    val maxLevel: Int,
    val maxSkillRank: Int,
) {
    One(value = 1, minLevel = 1, maxLevel = 5, maxSkillRank = 2),
    Two(value = 2, minLevel = 6, maxLevel = 10, maxSkillRank = 3),
    Three(value = 3, minLevel = 11, maxLevel = 15, maxSkillRank = 4),
    Four(value = 4, minLevel = 16, maxLevel = 20, maxSkillRank = 5),
    Five(value = 5, minLevel = 21, maxLevel = Int.MAX_VALUE, maxSkillRank = 5);

    companion object {
        fun fromLevel(level: Int): PlayTier = when {
            level <= 5 -> One
            level <= 10 -> Two
            level <= 15 -> Three
            level <= 20 -> Four
            else -> Five
        }
    }
}
