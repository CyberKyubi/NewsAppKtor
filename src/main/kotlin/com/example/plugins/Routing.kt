package com.example.plugins

import com.example.features.homepage.routing.articlepage.articlePage
import com.example.features.homepage.routing.newsfeed.newsFeed
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        newsFeed()
        articlePage()
    }
}
