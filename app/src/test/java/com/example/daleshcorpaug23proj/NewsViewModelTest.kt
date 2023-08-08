package com.example.daleshcorpaug23proj

import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest

import org.junit.runner.RunWith
import androidx.lifecycle.Observer
import com.example.daleshcorpaug23proj.news.service.NewsRepository
import com.example.daleshcorpaug23proj.news.view.news_articles.Article
import com.example.daleshcorpaug23proj.news.view.news_articles.ArticleResponse
import com.example.daleshcorpaug23proj.news.viewmodel.NewsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class NewsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val newsRepository = mockk<NewsRepository>()

    private val newsDataObserver = mockk<Observer<ArticleResponse>>(relaxed = true)

    private lateinit var viewModel: NewsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModel = NewsViewModel(newsRepository)
        viewModel.newsData.observeForever(newsDataObserver)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        viewModel.newsData.removeObserver(newsDataObserver)
    }

    @Test
    fun `fetchNewsData calls getNewsData in newsRepository`() {
        val page = 1

        val articles = listOf(
            Article(
                author = "Hugh Hefner",
                title = "Playboy",
                description = "MarilynMonroe",
                url = "Marilyn Black dress image",
                urlToImage = "https://blog.artsper.com/wp-content/uploads/2022/03/6-Marilyn-Monroe-New-York-City-6-mai-1957-Richard-Avedon-%C2%A9-Christies-2.jpg",
                publishedAt = "Playboy title 6",
                content = "MarilynMonroe content 6 xa "
            )
        )

        val response = ArticleResponse(
            status = "Published",
            totalResults = 1,
            articles= articles

        )

        coEvery { newsRepository.getNewsData(page) } returns response

        viewModel.fetchNewsData(page)

        verify { newsDataObserver.onChanged(response) }
    }
}
