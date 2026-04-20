package com.gannon.cosmere_kmp.rules.ruleTables

import com.gannon.cosmere_kmp.util.DiceGroup

sealed interface UnarmedDamage {
    data class FlatDamage(val value: Int): UnarmedDamage
    data class RolledDamage(val diceGroup: DiceGroup): UnarmedDamage
}