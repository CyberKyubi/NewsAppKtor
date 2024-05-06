package com.example.cache.model

data class NewsFeedUserSession(
    val token: String,
    val articles: MutableMap<Int, String>
)



