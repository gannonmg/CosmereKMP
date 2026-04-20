package com.gannon.cosmere_kmp.character.sheet

import com.gannon.cosmere_kmp.character.build.IdentifiedLevelUpChoice
import com.gannon.cosmere_kmp.character.engine.CharacterEngine
import com.gannon.cosmere_kmp.character.engine.CharacterBuild
import com.gannon.cosmere_kmp.rules.items.Item
import kotlinx.serialization.Serializable

@Serializable
data class CosmereCharacter(
    val name: String,
    var currentLevel: Int,
    val sheetState: CharacterSheetState = CharacterSheetState(),
    val levelUps: Map<Int, Array<IdentifiedLevelUpChoice>>,
) {
    fun toCharacterSheet(): CharacterSheet = CharacterSheet(
        build = CharacterEngine.deriveCharacterBuild(
            levelUps = levelUps,
            atLevel = currentLevel,
        ),
        state = sheetState,
    )
}

data class CharacterSheet(
    val build: CharacterBuild,
    val state: CharacterSheetState,
)

/**
 * A serializable object to persist the character sheet state between sessions.
 * Excludes values derived from level up.
 * */
@Serializable
data class CharacterSheetState(
    /** Primary expendable and refreshable resources. i.e. health, focus, investiture. */
    val characterResources: Map<ResourcePool, Int> = emptyMap(),
    val conditions: List<Condition> = emptyList(),
    val inventory: CharacterInventory = CharacterInventory(),
    val playerNotes: PlayerNotes = PlayerNotes(),
) {
    fun resource(resource: ResourcePool): Int? = characterResources[resource]

}

@Serializable
data class PlayerNotes(
    val notes: List<String> = emptyList(),
    val goals: List<CharacterGoal> = emptyList(),
    val purpose: String = "",
    val obstacle: String = "",
    val connections: List<String> = emptyList(),
)

@Serializable
data class CharacterInventory(
    val inventory: List<InventoryItem> = emptyList(),
    val marks: Int = 0,
    val infusedMarks: Int = 0,
)

@Serializable
data class CharacterGoal(
    val title: String,
    val progress: Int = 0,
)

@Serializable
data class InventoryItem(
    val item: Item,
    val quantity: Int = 1,
    val isEquipped: Boolean = false,
)

@Serializable
enum class ResourcePool {
    Health,
    Focus,
    Investiture,
}

@Serializable
enum class Condition {
    Slowed
}