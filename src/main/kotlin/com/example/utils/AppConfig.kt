package com.example.utils

import io.github.cdimascio.dotenv.dotenv

object AppConfig {
    private val dotenv = dotenv()

    val apiKey: String = dotenv["API_KEY"]
}