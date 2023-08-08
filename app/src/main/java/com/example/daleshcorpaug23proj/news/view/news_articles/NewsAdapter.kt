package com.example.daleshcorpaug23proj.news.view.news_articles // ktlint-disable package-name

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daleshcorpaug23proj.R
import com.example.daleshcorpaug23proj.news.view.articledetail.ArticleDetailActivity

class NewsAdapter(private val articles: MutableList<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.articleImageView)
        val titleView: TextView = view.findViewById(R.id.titleTextView)
        val authorView: TextView = view.findViewById(R.id.authorTextView)
        val descriptionView: TextView = view.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]

        holder.titleView.setText(article.title)
        holder.authorView.setText(article.author)
        holder.descriptionView.setText(article.description)

     //   holder.titleView.text = article.title
       // holder.authorView.text = article.author
        //holder.descriptionView.text = article.description

        // Load image from URL into ImageView using an image loading library.
        // Example with Glide:
     /*   Glide.with(holder.view.context)
            .load(article.urlToImage)
            .into(holder.imageView)

      */

        // Set click listener on the entire view
        holder.view.setOnClickListener {
            // Create an Intent to start ArticleDetailActivity
            val intent = Intent(holder.view.context, ArticleDetailActivity::class.java)

            // Pass the Article object to the new activity
            intent.putExtra("article", article)

            // Start the new activity
            holder.view.context.startActivity(intent)
        }
    }

    fun updateArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    // notifyDataSetChanged method internally triggers the RecyclerView.AdapterDataObservable.notifyChanged method which is not set in the unit test environment. In Android, this kind of method is often tied to the Android UI framework and doesn't work well in unit tests.
    // Testing purposes
    fun updateArticlesForTest(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
    }
}
