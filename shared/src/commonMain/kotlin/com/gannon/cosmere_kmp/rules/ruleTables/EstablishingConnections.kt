package com.gannon.cosmere_kmp.rules.ruleTables

sealed interface EstablishingConnections {
    data class Years(val value: Int) : EstablishingConnections
    data class Days(val value: Int) : EstablishingConnections
    data class Hours(val value: Int) : EstablishingConnections
    data object ReputationPrecedesYou : EstablishingConnections
}
