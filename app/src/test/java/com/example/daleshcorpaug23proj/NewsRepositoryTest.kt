package com.example.daleshcorpaug23proj
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.example.daleshcorpaug23proj.news.retrofit.RetroService
import com.example.daleshcorpaug23proj.news.service.NewsRepository
import com.example.daleshcorpaug23proj.news.service.NewsRepositoryImpl
import com.example.daleshcorpaug23proj.news.view.news_articles.Article
import com.example.daleshcorpaug23proj.news.view.news_articles.ArticleResponse
import com.example.daleshcorpaug23proj.news.view.news_articles.NewsAdapter
import io.mockk.* // ktlint-disable no-wildcard-imports
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config

class NewsRepositoryTest {

    private val retroService = mockk<RetroService>()
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        newsRepository = NewsRepositoryImpl(retroService)
    }

    @Test
    fun `test getNewsData is called once`() = runBlockingTest {
        val articleResponse = mockk<ArticleResponse>()
        val page = 1
        coEvery { retroService.getNewsData(page = page) } returns articleResponse

        newsRepository.getNewsData(page)

        coVerify(exactly = 1) { retroService.getNewsData(page = page) }
    }
}
