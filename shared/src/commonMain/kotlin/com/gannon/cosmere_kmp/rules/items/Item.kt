package com.gannon.cosmere_kmp.rules.items

import kotlinx.serialization.Serializable

@Serializable
sealed interface Item {
    val id: String
    val name: String
    val price: ItemPrice
    val weight: ItemWeight?
    val kind: ItemKind
}

@Serializable
enum class ItemKind {
    Weapon, Armor, Mount, Vehicle, Fabrial, Miscellaneous,
}

@Serializable
sealed interface ItemWeight {
    data class Pounds(val amount: Int) : ItemWeight
    data object Weightless : ItemWeight
}

@Serializable
sealed interface ItemPrice {
    data class Marks(val amount: Int) : ItemPrice
    data object TalentOnly : ItemPrice
    data object RewardOnly : ItemPrice
}