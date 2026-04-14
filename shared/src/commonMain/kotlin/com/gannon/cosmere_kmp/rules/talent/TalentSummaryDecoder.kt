//package com.gannon.cosmere_kmp.rules.talent
//
//import kotlinx.serialization.Serializable
//import kotlinx.serialization.json.Json
//
///** Decodes bundled talent JSON into minimal talent summaries. */
//object TalentSummaryDecoder {
//    private val json = Json {
//        ignoreUnknownKeys = true
//    }
//
//    /** Decodes one talent listing JSON document. */
//    fun decodeTalentSummaries(payload: String): List<TalentSummary> =
//        json.decodeFromString<TalentListingResponse>(payload)
//            .results
//            .flatMap { it.hits }
//            .map { hit ->
//                TalentSummary(
//                    name = hit.name,
//                    shortDescription = hit.shortDescription,
//                    slug = hit.slug
//                )
//            }
//}
//
///** Top-level response wrapper from the exported talent listing JSON. */
//@Serializable
//private data class TalentListingResponse(
//    val results: List<TalentListingResult>
//)
//
///** Result bucket containing talent hits. */
//@Serializable
//private data class TalentListingResult(
//    val hits: List<TalentListingHit>
//)
//
///** Minimal hit fields needed for early talent work. */
//@Serializable
//private data class TalentListingHit(
//    val name: String,
//    val shortDescription: String,
//    val slug: String
//)
