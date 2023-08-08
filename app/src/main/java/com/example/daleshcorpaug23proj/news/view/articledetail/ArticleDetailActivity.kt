package com.example.daleshcorpaug23proj.news.view.articledetail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.daleshcorpaug23proj.R
import com.example.daleshcorpaug23proj.news.view.news_articles.Article

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_details)

        // Get the article passed in from NewsActivity
        val article: Article = intent.getSerializableExtra("article") as Article

        // Find the views in the layout
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val articleImageView: ImageView = findViewById(R.id.articleImageView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val authorTextView: TextView = findViewById(R.id.authorTextView)

        // Populate the views with the article data
        titleTextView.text = article.title
        Glide.with(this)
            .load(article.urlToImage)
            .into(articleImageView)
        descriptionTextView.text = article.description
        authorTextView.text = article.author
    }
}
