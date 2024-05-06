package com.example.features.homepage.routing.newsfeed.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsFeedSuccessRespond(
    val token: String,
    val articles: List<NewsArticleRespond>
)

@Serializable
data class NewsArticleRespond(
    val id: Int,
    val source: Source,
    val author: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
)
