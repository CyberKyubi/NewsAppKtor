package com.example.features.homepage.routing.articlepage

import com.example.cache.CacheManager
import com.example.features.homepage.routing.articlepage.model.ArticlePageReceive
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.articlePage() {
    route("/articlePage") {
        get {
            val receive = call.receive<ArticlePageReceive>()
            when (val articlePage = CacheManager().getArticlePage(receive)) {
                null -> call.respond(HttpStatusCode.BadRequest, "Article page not found")
                else -> call.respond(HttpStatusCode.OK, articlePage)
            }
        }
    }
}

