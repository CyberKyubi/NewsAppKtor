package com.example.features.homepage.routing.articlepage.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticlePageReceive (
    val token: String,
    val id: Int
)