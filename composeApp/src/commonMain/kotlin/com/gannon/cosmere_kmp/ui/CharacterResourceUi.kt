package com.gannon.cosmere_kmp.ui

import androidx.compose.ui.graphics.Color
import com.gannon.cosmere_kmp.character.sheet.ResourcePool

// TODO: Replace these temporary resource colors with real sheet/theme tokens when the visual system is defined.
val ResourcePool.accentColor: Color
    get() = when (this) {
        ResourcePool.Health -> Color(0xFFFFE1DE)
        ResourcePool.Focus -> Color(0xFFE8ECFF)
        ResourcePool.Investiture -> Color(0xFFFFF1CC)
    }

val ResourcePool.label: String
    get() = when (this) {
        ResourcePool.Health -> "Health"
        ResourcePool.Focus -> "Focus"
        ResourcePool.Investiture -> "Investiture"
    }
