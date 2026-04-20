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
            price = ItemPrice.Marks(10),
        )
    }
}

@Serializable
sealed interface AttackRange {
    data class Melee(val bonus: Int = 0) : AttackRange
    data class Ranged(val short: Int, val long: Int) : AttackRange
}

@Serializable
sealed interface WeaponTrait {
    data class Thrown(val short: Int, val long: Int) : WeaponTrait
    data class Removes(val trait: WeaponTrait) : WeaponTrait
    data class Range(val range: AttackRange) : WeaponTrait
    data object Discreet : WeaponTrait
    data object Quickdraw : WeaponTrait
    data object Indirect: WeaponTrait
    data object Defensive : WeaponTrait
    data object Momentum : WeaponTrait
    data object Deadly : WeaponTrait
    data object Dangerous : WeaponTrait
}

@Serializable
enum class DamageType {
    Keen, Impact, Spirit
}