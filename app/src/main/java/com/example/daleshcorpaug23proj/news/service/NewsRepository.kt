package com.example.daleshcorpaug23proj.news.service

import com.example.daleshcorpaug23proj.news.retrofit.RetroService
import com.example.daleshcorpaug23proj.news.view.news_articles.ArticleResponse

interface NewsRepository {
    suspend fun getNewsData(page: Int): ArticleResponse
}

class NewsRepositoryImpl(private val retroService: RetroService) : NewsRepository {
    override suspend fun getNewsData(page: Int): ArticleResponse {
        return retroService.getNewsData(page = page)
    }
}
