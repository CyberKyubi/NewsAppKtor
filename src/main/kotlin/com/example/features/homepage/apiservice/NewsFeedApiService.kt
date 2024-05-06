package com.example.features.homepage.apiservice

import com.example.cache.model.NewsFeedUserSession
import com.example.features.homepage.routing.newsfeed.model.*
import com.example.features.homepage.state.RespondState
import com.example.utils.AppConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class NewsFeedApiService(
    private val params: UserParamsNewsFeedReceive,
    private val cache: NewsFeedUserSession
) {
    private var client: HttpClient = KtorClient().createClient()
    private val apiKey = AppConfig.apiKey


    suspend fun getNewsFeed(): RespondState {
        val response = getResponse()

        if (response.status.value !in 200..299) {
            val body = response.body<NewsFeedErrorResponse>()
            return RespondState.Error(body.message)
        }

        val body = response.body<NewsFeedSuccessResponse>()
        if (isEmptyResult(body)) {
            return RespondState.Error("Nothing found")
        }

        val newsFeed = preparingFeed(body.articles)
        return RespondState.Success(NewsFeedSuccessRespond(token = cache.token, articles = newsFeed))
    }

    private suspend fun getResponse(): HttpResponse {
        return client.get("everything?") {
            url {
                parameters.append("q", createQParam())
                parameters.append("apiKey", apiKey)
                parameters.append("pageSize", "10")
                parameters.append("page", params.page.toString())
                parameters.append("sortBy", "publishedAt")
                parameters.append("language", "en")
            }
        }
    }

    private fun createQParam(): String {
        println(params.topics.toString())
        return if (params.topics.isEmpty()) "a" else params.topics.joinToString(separator = " OR ")
    }

    private fun isEmptyResult(body: NewsFeedSuccessResponse): Boolean = body.totalResults == 0

    private fun preparingFeed(response: List<NewsArticleResponse>): List<NewsArticleRespond> {
        val clearedArticles = response.filter { it != nullableArticleResponse }
        if (clearedArticles.size <= 5) {
            loadMore()
        }

        val offset = if (cache.articles.isEmpty()) 0 else cache.articles.size
        val newsFeed = clearedArticles.mapIndexed { index, article ->
            val id = index + offset
            cache.articles[id] = article.content

            NewsArticleRespond(
                id = id,
                source = article.source,
                author = article.author ?: "",
                title = article.title,
                url = article.url,
                urlToImage = article.urlToImage ?: "skipped",
                publishedAt = article.publishedAt,
            )
        }
        return newsFeed
    }


    private fun loadMore() {}
}