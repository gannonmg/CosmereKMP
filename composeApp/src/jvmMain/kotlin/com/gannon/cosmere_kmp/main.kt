package com.gannon.cosmere_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Cosmere KMP",
    ) {
        App()
    }
}