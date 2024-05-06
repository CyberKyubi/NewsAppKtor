package com.example.features.homepage.routing.newsfeed

import com.example.cache.CacheManager
import com.example.cache.model.NewsFeedUserSession
import com.example.features.homepage.apiservice.NewsFeedApiService
import com.example.features.homepage.routing.newsfeed.model.UserParamsNewsFeedReceive
import com.example.features.homepage.state.RespondState
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.newsFeed() {
    route("/newsFeed") {
        post {
            val receive = call.receive<UserParamsNewsFeedReceive>()
            val cache = checkCache(receive.token)
            when (val feed = getNewsFeed(receive, cache)) {
                is RespondState.Success -> {
                    call.respond(HttpStatusCode.OK, feed.data)
                }
                is RespondState.Error -> {
                    call.respond(HttpStatusCode.BadRequest, feed.error)
                }
            }
        }
    }
}


fun checkCache(token: String?): NewsFeedUserSession {
    return CacheManager().findOrCreateSession(token)
}

suspend fun getNewsFeed(params: UserParamsNewsFeedReceive, cache: NewsFeedUserSession): RespondState {
    return NewsFeedApiService(params, cache).getNewsFeed()
}
