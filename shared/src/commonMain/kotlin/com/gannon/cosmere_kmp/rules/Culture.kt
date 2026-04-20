package com.gannon.cosmere_kmp.rules

enum class Culture {
    // Cultures
    Alethi, Azish, Herdazian, Iriali, Kharbranthian, Listener, Natan, Reshi, Shin, Singer, Thaylen, Unkalaki, Veden,
    // Subcultures
    HighSociety, Underworld, Military, Wayfarer, CriminalGroups;

    val cultureType: CultureType
        get() = when (this) {
            HighSociety, Underworld, Military, Wayfarer, CriminalGroups -> CultureType.Subculture
            else -> CultureType.Nationality
    }
}

enum class CultureType {
    Nationality, Subculture
}