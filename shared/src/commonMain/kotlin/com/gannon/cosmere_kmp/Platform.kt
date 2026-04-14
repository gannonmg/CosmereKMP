package com.gannon.cosmere_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform