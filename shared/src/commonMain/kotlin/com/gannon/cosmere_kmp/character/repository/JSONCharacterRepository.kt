//package com.gannon.cosmerestatsheet_kmp.character.repository
//
//import com.gannon.cosmere_kmp.character.repository.CharacterRepository
//import com.gannon.cosmerestatsheet_kmp.persistence.CharacterDocument
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.serialization.json.Json
//import okio.FileSystem
//import okio.Path
//import okio.Path.Companion.toPath
//import okio.buffer
//
///** CharacterRepository JSON Implementation */
//class JSONCharacterRepository(
//    private val fileSystem: FileSystem,
//    filePath: String,
//    private val defaultCharacterDocument: () -> CharacterDocument,
//    private val json: Json = Json {
//        prettyPrint = true
//        ignoreUnknownKeys = true
//    }
//) : CharacterRepository {
//    private val path: Path = filePath.toPath()
//    private val characterFlow = MutableStateFlow(readCharacterOrNull())
//
//    override fun observeDocument(): Flow<CharacterDocument?> = characterFlow.asStateFlow()
//
//    override fun currentDocument(): CharacterDocument? = characterFlow.value
//
//    override suspend fun saveDocument(characterDocument: CharacterDocument) {
//        writeCharacter(characterDocument)
//        characterFlow.value = characterDocument
//    }
//
//    override suspend fun createDefaultDocumentIfMissing() {
//        if (characterFlow.value != null) return
//
//        val character = defaultCharacterDocument()
//        writeCharacter(character)
//        characterFlow.value = character
//    }
//
//    private fun readCharacterOrNull(): CharacterDocument? {
//        if (!fileSystem.exists(path)) return null
//
//        return runCatching {
//            fileSystem.source(path).buffer().use { source ->
//                json.decodeFromString<CharacterDocument>(source.readUtf8())
//            }
//        }.getOrNull()
//    }
//
//    private fun writeCharacter(characterDocument: CharacterDocument) {
//        path.parent?.let { parent ->
//            if (!fileSystem.exists(parent)) {
//                fileSystem.createDirectories(parent)
//            }
//        }
//
//        fileSystem.sink(path).buffer().use { sink ->
//            sink.writeUtf8(json.encodeToString(characterDocument))
//        }
//    }
//}
