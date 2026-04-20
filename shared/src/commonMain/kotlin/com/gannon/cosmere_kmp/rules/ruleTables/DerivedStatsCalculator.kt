package com.gannon.cosmere_kmp.rules.ruleTables

import com.gannon.cosmere_kmp.util.DamageDice
import com.gannon.cosmere_kmp.util.Dice
import com.gannon.cosmere_kmp.util.DiceGroup

object DerivedStatsCalculator {
    fun tierForLevel(level: Int): PlayTier = PlayTier.fromLevel(level)

    fun maxSkillRankForLevel(level: Int): Int =
        tierForLevel(level).maxSkillRank

    fun focus(willpower: Int): Int =
        2 + willpower

    fun investiture(presence: Int, awareness: Int): Int =
        2 + maxOf(presence, awareness)

    fun movementFeetPerAction(speed: Int): MovementRate = when {
        speed <= 0 -> MovementRate(20)
        speed <= 2 -> MovementRate(25)
        speed <= 4 -> MovementRate(30)
        speed <= 6 -> MovementRate(40)
        speed <= 8 -> MovementRate(60)
        else -> MovementRate(80)
    }

    fun recoveryDie(willpower: Int): Dice = when {
        willpower <= 0 -> Dice.D4
        willpower <= 2 -> Dice.D6
        willpower <= 4 -> Dice.D8
        willpower <= 6 -> Dice.D10
        willpower <= 8 -> Dice.D12
        else -> Dice.D20
    }

    fun sensesRange(awareness: Int): SensesRange = when {
        awareness <= 0 -> SensesRange.Feet(5)
        awareness <= 2 -> SensesRange.Feet(10)
        awareness <= 4 -> SensesRange.Feet(20)
        awareness <= 6 -> SensesRange.Feet(50)
        awareness <= 8 -> SensesRange.Feet(100)
        else -> SensesRange.UnaffectedByObscuredSenses
    }

    fun establishingConnections(presence: Int): EstablishingConnections = when {
        presence <= 0 -> EstablishingConnections.Years(1)
        presence <= 2 -> EstablishingConnections.Days(50)
        presence <= 4 -> EstablishingConnections.Days(5)
        presence <= 6 -> EstablishingConnections.Days(1)
        presence <= 8 -> EstablishingConnections.Hours(1)
        else -> EstablishingConnections.ReputationPrecedesYou
    }

    fun unarmedAttack(strength: Int): UnarmedDamage = when {
        strength <= 2 -> UnarmedDamage.FlatDamage(1)
        strength <= 4 -> UnarmedDamage.RolledDamage(DiceGroup(Dice.D4))
        strength <= 6 -> UnarmedDamage.RolledDamage(DiceGroup(Dice.D8))
        strength <= 8 -> UnarmedDamage.RolledDamage(DiceGroup(Dice.D6, amount = 2))
        else -> UnarmedDamage.RolledDamage(DiceGroup(Dice.D10, amount = 2))
    }
}