package com.gannon.cosmere_kmp.util

import com.gannon.cosmere_kmp.rules.items.DamageType
import kotlinx.serialization.Serializable

@Serializable
enum class Dice {
    D2, D4, D6, D8, D10, D12, D20;
}

@Serializable
data class DiceGroup(
    val dice: Dice,
    val amount: Int = 1
)

@Serializable
data class DamageDice(
    val diceRoll: List<DiceGroup>,
    val damageType: DamageType,
)

