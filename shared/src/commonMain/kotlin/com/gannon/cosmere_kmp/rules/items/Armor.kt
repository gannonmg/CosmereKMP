package com.gannon.cosmere_kmp.rules.items

import kotlinx.serialization.Serializable

@Serializable
data class Armor(
    override val id: String,
    override val name: String,
    override val weight: ItemWeight,
    override val price: ItemPrice,
    val deflect: Int,
    val traits: List<ArmorTrait>,
    val expertTraits: List<ArmorTrait>,
) : Item {
    override val kind: ItemKind = ItemKind.Armor

    companion object {
        val uniform = Armor(
            id = "armor.uniform",
            name = "Uniform",
            deflect = 0,
            traits = listOf(ArmorTrait.Presentable),
            expertTraits = emptyList(),
            weight = ItemWeight.Pounds(5),
            price = ItemPrice.Marks(40),
        )

        val chain = Armor(
            id = "armor.chain",
            name = "Chain",
            deflect = 2,
            traits = listOf(ArmorTrait.Cumbersome(3)),
            expertTraits = listOf(ArmorTrait.Removes(ArmorTrait.Cumbersome(3))),
            weight = ItemWeight.Pounds(25),
            price = ItemPrice.Marks(80),
        )
    }
}

@Serializable
sealed interface ArmorTrait {
    data class Cumbersome(val requiredStrength: Int) : ArmorTrait
    data class Removes(val trait: ArmorTrait) : ArmorTrait
    data object Presentable : ArmorTrait
}
