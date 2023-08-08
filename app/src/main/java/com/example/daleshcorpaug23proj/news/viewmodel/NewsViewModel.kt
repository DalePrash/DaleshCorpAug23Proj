package com.example.daleshcorpaug23proj.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daleshcorpaug23proj.news.service.NewsRepository
import com.example.daleshcorpaug23proj.news.view.news_articles.ArticleResponse
import kotlinx.coroutines.launch
class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    // LiveData to hold the news data
    val newsData = MutableLiveData<ArticleResponse>()
    fun fetchNewsData(page: Int) {
        viewModelScope.launch {
            val response = newsRepository.getNewsData(page)
            newsData.value = response
        }
    }
}
