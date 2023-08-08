package com.example.daleshcorpaug23proj.news.view.news_articles // ktlint-disable package-name
import java.io.Serializable

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Serializable

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Serializable
