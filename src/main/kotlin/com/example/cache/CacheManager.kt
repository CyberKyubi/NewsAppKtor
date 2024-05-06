package com.example.cache

import com.example.cache.model.NewsFeedUserSession
import com.example.features.homepage.routing.articlepage.model.ArticlePageReceive
import java.util.UUID

val cache = mutableMapOf<String, MutableMap<Int, String>>()

class CacheManager {
    fun findOrCreateSession(token: String?) : NewsFeedUserSession {
        return when {
            token == null -> {
                val newToken = (UUID.randomUUID()).toString()
                NewsFeedUserSession(newToken, getOrPutSession(newToken))
            }
            else -> {
                NewsFeedUserSession(token, getOrPutSession(token))
            }
        }
    }

    private fun getOrPutSession(token: String): MutableMap<Int, String> {
        return cache.getOrPut(token) { mutableMapOf() }
    }

    fun getArticlePage(params: ArticlePageReceive): String? {
        return cache[params.token]?.getOrDefault(params.id, null)

    }
}

