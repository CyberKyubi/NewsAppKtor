package com.example.features.homepage.state

import com.example.features.homepage.routing.newsfeed.model.NewsFeedSuccessRespond

sealed class RespondState {
    data class Success(val data: NewsFeedSuccessRespond): RespondState()
    data class Error(val error: String): RespondState()
}