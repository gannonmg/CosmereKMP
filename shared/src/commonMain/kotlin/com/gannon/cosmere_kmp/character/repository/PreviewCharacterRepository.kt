//package com.gannon.cosmere_kmp.character.repository
//
//import com.gannon.cosmere_kmp.character.repository.CharacterRepository
//import com.gannon.cosmerestatsheet_kmp.persistence.CharacterDocument
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//
//class PreviewCharacterRepository(
//    private val defaultCharacter: () -> CharacterDocument
//) : CharacterRepository {
//
//    private val characterFlow = MutableStateFlow<CharacterDocument?>(null)
//
//    override fun observeDocument(): Flow<CharacterDocument?> = characterFlow.asStateFlow()
//
//    override fun currentDocument(): CharacterDocument? = characterFlow.value
//
//    override suspend fun saveDocument(characterDocument: CharacterDocument) {
//        characterFlow.value = characterDocument
//    }
//
//    override suspend fun createDefaultDocumentIfMissing() {
//        if (characterFlow.value == null) {
//            characterFlow.value = defaultCharacter()
//        }
//    }
//}
