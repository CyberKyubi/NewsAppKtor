package com.example.features.homepage.routing.newsfeed.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsFeedErrorResponse(
    val status: String,
    val code: String,
    val message: String
)

@Serializable
data class NewsFeedSuccessResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticleResponse>
)

@Serializable
data class NewsArticleResponse(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String,
)


val nullableArticleResponse = NewsArticleResponse(
    source = Source (
        id = null,
        name = "[Removed]"
    ),
    author = null,
    title = "[Removed]",
    description = "[Removed]",
    url = "https://removed.com",
    urlToImage = null,
    publishedAt = "1970-01-01T00:00:00Z",
    content = "[Removed]"
)
