package com.example.daleshcorpaug23proj.news.view.news_articles // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daleshcorpaug23proj.news.service.NewsRepository
import com.example.daleshcorpaug23proj.news.viewmodel.NewsViewModel

class NewsViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

