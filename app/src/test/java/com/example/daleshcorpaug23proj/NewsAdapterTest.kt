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
import com.example.daleshcorpaug23proj.news.view.news_articles.Article
import com.example.daleshcorpaug23proj.news.view.news_articles.NewsAdapter
import io.mockk.* // ktlint-disable no-wildcard-imports
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NewsAdapterTest {
    private lateinit var mockView: View
    private lateinit var mockImageView: ImageView
    private lateinit var mockTextView: TextView
    private lateinit var mockTextViewTitle: TextView


    private lateinit var mockContext: Context
    private lateinit var adapter: NewsAdapter
    private lateinit var viewHolder: NewsAdapter.NewsViewHolder

    @Before
    fun setup() {
        val mockTextViewTitle: TextView = TextView(RuntimeEnvironment.systemContext)
        mockView = mockk(relaxed = true)
        mockImageView = mockk(relaxed = true)
        mockTextView = mockk(relaxed = true)
        mockContext = mockk(relaxed = true)
        mockkStatic("android.view.LayoutInflater")
        //mockTextViewTitle = mockk(relaxed = true)

        every { mockView.context } returns mockContext
        every { mockView.findViewById<ImageView>(R.id.articleImageView) } returns mockImageView
      //  every { mockView.findViewById<TextView>(R.id.titleTextView) } returns mockTextView
        every { mockView.findViewById<TextView>(R.id.authorTextView) } returns mockTextView
        every { mockView.findViewById<TextView>(R.id.descriptionTextView) } returns mockTextView
        every { mockView.findViewById<TextView>(R.id.titleTextView) } returns mockTextViewTitle

        viewHolder = NewsAdapter.NewsViewHolder(mockView)
        adapter = NewsAdapter(mutableListOf())
    }

    @Test
    fun `updateArticles updates the article list and notifies data set changed`() {

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
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `onCreateViewHolder creates and returns NewsViewHolder`() {
        val mockLayoutInflater: LayoutInflater = mockk(relaxed = true)

        val parent = mockk<ViewGroup>()
        every { parent.context } returns mockContext
        val layoutInflater = mockk<LayoutInflater>()
        every { LayoutInflater.from(mockContext) } returns layoutInflater
        every { layoutInflater.inflate(R.layout.news_item, parent, false) } returns mockView
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        every { LayoutInflater.from(any()) } returns mockLayoutInflater

        assertNotNull(viewHolder)
        assertTrue(viewHolder is NewsAdapter.NewsViewHolder)
    }

    @Test
    fun `onBindViewHolder binds data to ViewHolder x`() {
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

        every { mockTextView.text = any<String>() } just Runs

        adapter.onBindViewHolder(viewHolder, 0)

        verify { mockTextView.text = "Playboy" }
        verify { mockTextView.text = "Hugh Hefner" }
        verify { mockTextView.text = "MarilynMonroe" }

        // Use Robolectric's shadow to get the Drawable set on the ImageView
    //    val shadowImageView = Shadows.shadowOf(mockImageView)
     //   val drawable = shadowImageView.getDrawable()


        // Use Robolectric's shadow to get the Drawable set on the ImageView
        val shadowImageView = Shadows.shadowOf(mockImageView)
      //  val drawable = shadowImageView.getImageDrawable()

        // Here you can add assertions to verify the drawable if needed
        // For example, verify that a drawable was set
        //assertNotNull(drawable)

        // Here you can add assertions to verify the drawable if needed
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
        val context = RuntimeEnvironment.systemContext
      //  every { Glide.with(context) } returns requestManager

        //    every { Glide.with(any<Context>()) } returns requestManager
        every { requestManager.load(any<String>()) } returns requestBuilder
        every { requestBuilder.into(any<ImageView>()) } returns mockk()

        every { mockTextView.text = any<String>() } just Runs

        adapter.onBindViewHolder(viewHolder, 0)

        verify { mockTextView.text = "Playboy" }
        verify { mockTextView.text = "Hugh Hefner" }
        verify { mockTextView.text = "MarilynMonroe" }
    }

    @Test
    fun `getItemCount returns correct item count`() {
        val articles = listOf(
            Article(
                author = "Hugh Hefner",
                title = "Playboy",
                description = "MarilynMonroe",
                url = "Marilyn Black dress image",
                urlToImage = "https://blog.artsper.com/wp-content/uploads/2022/03/6-Marilyn-Monroe-New-York-City-6-mai-1957-Richard-Avedon-%C2%A9-Christies-2.jpg",
                publishedAt = "Playboy title 6",
                content = "MarilynMonroe content 6 xa "
            ),
            Article(
                author = "Author2",
                title = "Title2",
                description = "Description2",
                url = "URL2",
                urlToImage = "URLToImage2",
                publishedAt = "PublishedAt2",
                content = "Content2"
            )
        )
        adapter.updateArticlesForTest(articles)

        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `onBindViewHolder binds data to xxx ViewHolder`() {
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
        val context = RuntimeEnvironment.systemContext
    //    every { Glide.with(context) } returns requestManager
    //    every { requestManager.load(any<String>()) } returns requestBuilder
      //  every { requestBuilder.into(any<ImageView>()) } returns mockk()

        adapter.onBindViewHolder(viewHolder, 0)

        val titleShadowTextView = Shadows.shadowOf(mockTextView)
        val authorShadowTextView = Shadows.shadowOf(mockTextView)
        val descriptionShadowTextView = Shadows.shadowOf(mockTextView)

        assertEquals("Playboy", mockTextViewTitle.text.toString())

     //   assertEquals("Playboy", titleShadowTextView.text)
      //  assertEquals("Hugh Hefner", authorShadowTextView.text)
       // assertEquals("MarilynMonroe", descriptionShadowTextView.text)
    }


    @Test
    fun `onBindViewHolder binds data to  yx aViewHolder`() {
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

        val mockTextViewTitle: TextView = mockk(relaxed = true)
        val mockTextViewAuthor: TextView = mockk(relaxed = true)
        val mockTextViewDescription: TextView = mockk(relaxed = true)

        every { mockView.findViewById<TextView>(R.id.titleTextView) } returns mockTextViewTitle
        every { mockView.findViewById<TextView>(R.id.authorTextView) } returns mockTextViewAuthor
        every { mockView.findViewById<TextView>(R.id.descriptionTextView) } returns mockTextViewDescription

        every { mockTextViewTitle.text = any<String>() } just Runs
        every { mockTextViewAuthor.text = any<String>() } just Runs
        every { mockTextViewDescription.text = any<String>() } just Runs

        adapter.onBindViewHolder(viewHolder, 0)

        verify { mockTextViewTitle.text = "Playboy" }
    //    verify { mockTextViewAuthor.text = "Hugh Hefner" }
      //  verify { mockTextViewDescription.text = "MarilynMonroe" }
    }


    @Test
    fun `onBindViewHolder  yaya binds data to ViewHolder`() {
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

        val mockTextViewTitle: TextView = mockk(relaxed = true)
        val mockTextViewAuthor: TextView = mockk(relaxed = true)
        val mockTextViewDescription: TextView = mockk(relaxed = true)

        every { mockView.findViewById<TextView>(R.id.titleTextView) } returns mockTextViewTitle
        every { mockView.findViewById<TextView>(R.id.authorTextView) } returns mockTextViewAuthor
        every { mockView.findViewById<TextView>(R.id.descriptionTextView) } returns mockTextViewDescription

        val titleSlot = slot<String>()
        val authorSlot = slot<String>()
        val descriptionSlot = slot<String>()

        every { mockTextViewTitle.text = capture(titleSlot) } answers { }
        every { mockTextViewAuthor.text = capture(authorSlot) } answers { }
        every { mockTextViewDescription.text = capture(descriptionSlot) } answers { }

        adapter.onBindViewHolder(viewHolder, 0)

        assertEquals("Playboy", titleSlot.captured)
        assertEquals("Hugh Hefner", authorSlot.captured)
        assertEquals("MarilynMonroe", descriptionSlot.captured)
    }


    @Test
    fun `onBindViewHolder binds data nfnf to  yx aViewHolder`() {
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

        val mockTextViewTitle: TextView = mockk(relaxed = true)
        val mockTextViewAuthor: TextView = mockk(relaxed = true)
        val mockTextViewDescription: TextView = mockk(relaxed = true)

        every { mockView.findViewById<TextView>(R.id.titleTextView) } returns mockTextViewTitle
        every { mockView.findViewById<TextView>(R.id.authorTextView) } returns mockTextViewAuthor
        every { mockView.findViewById<TextView>(R.id.descriptionTextView) } returns mockTextViewDescription

        every { mockTextViewTitle.setText(any<String>()) } just Runs
        every { mockTextViewAuthor.setText(any<String>()) } just Runs
        every { mockTextViewDescription.setText(any<String>()) } just Runs

        adapter.onBindViewHolder(viewHolder, 0)
     //   verify { mockTextViewTitle.setText("Playboy") }
        verify { mockTextViewAuthor.text = "Hugh Hefner" }

      //  verify { mockTextViewTitle.setText("Playboy") }
//    verify { mockTextViewAuthor.setText("Hugh Hefner") }
//  verify { mockTextViewDescription.setText("MarilynMonroe") }
    }



}
