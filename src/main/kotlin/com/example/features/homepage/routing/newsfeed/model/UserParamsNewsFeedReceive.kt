package com.example.features.homepage.routing.newsfeed.model

import kotlinx.serialization.Serializable

@Serializable
data class UserParamsNewsFeedReceive(
    val token: String?,
    val topics: List<String>,
    val page: Int
)
