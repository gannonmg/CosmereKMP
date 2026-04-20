package com.gannon.cosmere_kmp.rules.ruleTables

import com.gannon.cosmere_kmp.util.Dice

data class DerivedStats(
    val tier: PlayTier,
    val maxSkillRank: Int,
    val health: Int,
    val focus: Int,
    val investiture: Int,
    val movementFeetPerAction: Int,
    val recoveryDie: Dice,
    val sensesRange: SensesRange,
    val establishingConnections: EstablishingConnections,
)
