package com.gannon.cosmere_kmp.rules.ruleTables

object HealthCalculator {
    fun healthForLevel(
        level: Int,
        strength: Int,
    ): Int {
        var total = 0

        for (currentLevel in 1..level) {
            val tier = PlayTier.fromLevel(currentLevel)
            val baseGain = when (tier) {
                PlayTier.One -> if (currentLevel == 1) 10 else 5
                PlayTier.Two -> 4
                PlayTier.Three -> 3
                PlayTier.Four -> 2
                PlayTier.Five -> 1
            }

            total += baseGain

            if (currentLevel == 1 || currentLevel == 6 || currentLevel == 11 || currentLevel == 16) {
                total += strength
            }
        }

        return total
    }
}