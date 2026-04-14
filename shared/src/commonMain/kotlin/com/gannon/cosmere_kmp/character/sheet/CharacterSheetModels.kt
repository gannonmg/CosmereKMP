//package com.gannon.cosmerestatsheet_kmp.character.sheet
//
//import kotlinx.serialization.Serializable
//
///**
// * A serializable object to persist the character sheet state between sessions.
// * Excludes values derived from level up.
// * */
//@Serializable
//data class CharacterSheetState (
//    /** Primary expendable and refreshable resources. i.e. health, focus, investiture. */
//    val characterResources: Map<CharacterResource, Int> = emptyMap(),
//    val playerNotes: List<String> = emptyList(),
//    val goal: List<String> = emptyList(),
//    val purpose: String = "",
//    val obstacle: String = "",
//    )
//
//enum class CharacterResource {
//    HEALTH, FOCUS, INVESTITURE
//}