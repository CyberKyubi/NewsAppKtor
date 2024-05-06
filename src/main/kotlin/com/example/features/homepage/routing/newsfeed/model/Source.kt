package com.example.features.homepage.routing.newsfeed.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?,
    val name: String
)