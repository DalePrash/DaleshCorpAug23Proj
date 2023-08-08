package com.example.daleshcorpaug23proj

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.example.daleshcorpaug23proj.news.view.news_articles.Article
import com.example.daleshcorpaug23proj.news.view.news_articles.NewsAdapter
import io.mockk.* // ktlint-disable no-wildcard-imports
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class XXTest {
    private lateinit var mockView: View
    private lateinit var mockImageView: ImageView
    private lateinit var mockTextViewTitle: TextView
    private lateinit var mockTextViewAuthor: TextView
    private lateinit var mockTextViewDescription: TextView
    private lateinit var mockContext: Context
    private lateinit var adapter: NewsAdapter
    private lateinit var viewHolder: NewsAdapter.NewsViewHolder

    @Before
    fun setup() {
        mockView = mockk(relaxed = true)
        mockImageView = mockk(relaxed = true)
        mockTextViewTitle = mockk(relaxed = true)
        mockTextViewAuthor = mockk(relaxed = true)
        mockTextViewDescription = mockk(relaxed = true)
        mockContext = mockk(relaxed = true)

        every { mockView.context } returns mockContext
        every { mockView.findViewById<ImageView>(R.id.articleImageView) } returns mockImageView
        every { mockView.findViewById<TextView>(R.id.titleTextView) } returns mockTextViewTitle
        every { mockView.findViewById<TextView>(R.id.authorTextView) } returns mockTextViewAuthor
        every { mockView.findViewById<TextView>(R.id.descriptionTextView) } returns mockTextViewDescription

        viewHolder = NewsAdapter.NewsViewHolder(mockView)
        adapter = NewsAdapter(mutableListOf())
    }

    @Test
    fun `onBindViewHolder binds data to ViewHolder`() {
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
        adapter.updateArticlesForTest(articles)

        // Mock Glide
        val requestManager: RequestManager = mockk(relaxed = true)
        val requestBuilder: RequestBuilder<Drawable> = mockk(relaxed = true)
        //  val target: Target<Drawable> = mockk(relaxed = true)

        //   every { Glide.with(any()) } returns requestManager
        every { requestManager.load(any<String>()) } returns requestBuilder
        //  every { requestBuilder.into(any()) } returns target

        every { mockTextViewTitle.text = any<String>() } just Runs
        every { mockTextViewAuthor.text = any<String>() } just Runs
        every { mockTextViewDescription.text = any<String>() } just Runs

        adapter.onBindViewHolder(viewHolder, 0)

        verify { mockTextViewTitle.text = "Playboy" }
        verify { mockTextViewAuthor.text = "Hugh Hefner" }
        verify { mockTextViewDescription.text = "MarilynMonroe" }
    }

    @Test
    fun `onBindViewHolder xy binds data to ViewHolder`() {
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
        adapter.updateArticlesForTest(articles)

        // Mock Glide
        val requestManager: RequestManager = mockk(relaxed = true)
        val requestBuilder: RequestBuilder<Drawable> = mockk(relaxed = true)

        every { Glide.with(any<Context>()) } returns requestManager
        every { requestManager.load(any<String>()) } returns requestBuilder
        every { requestBuilder.into(any<ImageView>()) } returns mockk()

        every { mockTextViewTitle.text = any<String>() } just Runs
        every { mockTextViewAuthor.text = any<String>() } just Runs
        every { mockTextViewDescription.text = any<String>() } just Runs

        adapter.onBindViewHolder(viewHolder, 0)

        verify { mockTextViewTitle.text = "Playboy" }
        verify { mockTextViewAuthor.text = "Hugh Hefner" }
        verify { mockTextViewDescription.text = "MarilynMonroe" }
    }
}
