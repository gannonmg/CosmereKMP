package com.gannon.cosmere_kmp.rules.ruleTables

import com.gannon.cosmere_kmp.util.DiceGroup

sealed interface SensesRange {
    data class Feet(val value: Int) : SensesRange
    data object UnaffectedByObscuredSenses : SensesRange
}