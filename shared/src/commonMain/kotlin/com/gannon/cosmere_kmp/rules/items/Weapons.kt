package com.gannon.cosmere_kmp.rules.items

import com.gannon.cosmere_kmp.rules.skill.Skill
import com.gannon.cosmere_kmp.util.DamageDice
import com.gannon.cosmere_kmp.util.Dice
import com.gannon.cosmere_kmp.util.DiceGroup
import kotlinx.serialization.Serializable

@Serializable
data class Weapon(
    override val id: String,
    override val name: String,
    override val weight: ItemWeight,
    override val price: ItemPrice,
    val damageDice: List<DamageDice>,
    val attackRange: AttackRange,
    val skill: Skill,
    val traits: List<WeaponTrait>,
    val expertTraits: List<WeaponTrait>,
) : Item {
    override val kind: ItemKind = ItemKind.Weapon

    companion object {
        val javelin = Weapon(
            id = "weapon.javelin",
            name = "Javelin",
            damageDice = listOf(DamageDice(
                diceRoll = listOf(DiceGroup(Dice.D6)),
                damageType = DamageType.Keen
            )),
            attackRange = AttackRange.Melee(),
            skill = Skill.LightWeaponry,
            traits = listOf(WeaponTrait.Thrown(short = 30, long = 120)),
            expertTraits = listOf(WeaponTrait.Indirect),
            weight = ItemWeight.Pounds(2),
            price = ItemPrice.Marks(20),
        )

        val dagger = Weapon(
            id = "weapon.dagger",
            name = "Dagger",
            damageDice = listOf(DamageDice(
                diceRoll = listOf(DiceGroup(Dice.D4)),
                damageType = DamageType.Keen
            )),
            attackRange = AttackRange.Melee(),
            skill = Skill.LightWeaponry,
            traits = listOf(WeaponTrait.Discreet),
            expertTraits = listOf(WeaponTrait.Offhand, WeaponTrait.Thrown(30, 120)),
            weight = ItemWeight.Pounds(1),
            price = ItemPrice.Marks(8),
        )

        val staff = Weapon(
            id = "weapon.staff",
            name = "Staff",
            damageDice = listOf(DamageDice(
                diceRoll = listOf(DiceGroup(Dice.D6)),
                damageType = DamageType.Impact
            )),
            attackRange = AttackRange.Melee(),
            skill = Skill.LightWeaponry,
            traits = listOf(WeaponTrait.Discreet, WeaponTrait.TwoHanded),
            expertTraits = listOf(WeaponTrait.Defensive),
            weight = ItemWeight.Pounds(4),
            price = ItemPrice.Marks(1),
        )

        val shortbow = Weapon(
            id = "weapon.shortbow",
            name = "Shortbow",
            damageDice = listOf(DamageDice(
                diceRoll = listOf(DiceGroup(Dice.D6)),
                damageType = DamageType.Keen
            )),
            attackRange = AttackRange.Ranged(80, 320),
            skill = Skill.LightWeaponry,
            traits = listOf(WeaponTrait.TwoHanded),
            expertTraits = listOf(WeaponTrait.Quickdraw),
            weight = ItemWeight.Pounds(2),
            price = ItemPrice.Marks(80),
        )

        val greatsword = Weapon(
            id = "weapon.greatsword",
            name = "Greatsword",
            damageDice = listOf(DamageDice(
                diceRoll = listOf(DiceGroup(Dice.D10)),
                damageType = DamageType.Keen
            )),
            attackRange = AttackRange.Melee(),
            skill = Skill.HeavyWeaponry,
            traits = listOf(WeaponTrait.TwoHanded),
            expertTraits = listOf(WeaponTrait.Deadly),
            weight = ItemWeight.Pounds(7),
            price = ItemPrice.Marks(200),
        )


    }
}

@Serializable
sealed interface AttackRange {
    data class Melee(val bonus: Int = 0) : AttackRange
    data class Ranged(val short: Int, val long: Int) : AttackRange

    val displayLabel: String
        get() = when (this) {
            is Melee ->
                if (bonus == 0) "Melee"
                else "Melee [+$bonus]"
            is Ranged -> "Ranged [$short/$long]"
        }
}

@Serializable
sealed interface WeaponTrait {
    data class Thrown(val short: Int, val long: Int) : WeaponTrait
    data class Removes(val trait: WeaponTrait) : WeaponTrait
    data class Range(val range: AttackRange) : WeaponTrait
    data object Discreet : WeaponTrait
    data object TwoHanded : WeaponTrait
    data object Quickdraw : WeaponTrait
    data object Indirect: WeaponTrait
    data object Offhand: WeaponTrait
    data object Defensive : WeaponTrait
    data object Momentum : WeaponTrait
    data object Deadly : WeaponTrait
    data object Dangerous : WeaponTrait

    val displayLabel: String
        get() = when (this) {
            is WeaponTrait.Thrown -> "Thrown [$short/$long]"
            is WeaponTrait.Removes -> "Removes ${trait.displayLabel}"
            is WeaponTrait.Range -> "Range: ${range.displayLabel}"
            WeaponTrait.Discreet -> "Discreet"
            WeaponTrait.TwoHanded -> "Two-Handed"
            WeaponTrait.Quickdraw -> "Quickdraw"
            WeaponTrait.Indirect -> "Indirect"
            WeaponTrait.Offhand -> "Offhand"
            WeaponTrait.Defensive -> "Defensive"
            WeaponTrait.Momentum -> "Momentum"
            WeaponTrait.Deadly -> "Deadly"
            WeaponTrait.Dangerous -> "Dangerous"
        }
}

@Serializable
enum class DamageType {
    Keen, Impact, Spirit
}