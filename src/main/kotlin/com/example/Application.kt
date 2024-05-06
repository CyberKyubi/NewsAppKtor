package com.example

import com.example.cache.CacheManager
import com.example.plugins.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val dotenv = dotenv()
    dotenv.entries().forEach {
        System.setProperty(it.key, it.value)
    }
    EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
