package com.example.daleshcorpaug23proj.news.view.news_articles // ktlint-disable package-name

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.daleshcorpaug23proj.R
import com.example.daleshcorpaug23proj.databinding.ActivityNewsBinding
import com.example.daleshcorpaug23proj.news.retrofit.RetroInstance
import com.example.daleshcorpaug23proj.news.service.NewsRepositoryImpl
import com.example.daleshcorpaug23proj.news.viewmodel.NewsViewModel

class NewsActivity : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_news)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the ViewModel
        //     newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        // Initialize the ViewModel
        //  val newsRepository = NewsRepository(RetroInstance.getRetroInstance(RetroInstance.baseURL))

        val newsRepository = NewsRepositoryImpl(RetroInstance.service)

        val viewModelFactory = NewsViewModelFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        // Initialize the RecyclerView
     //   val recyclerView: RecyclerView = findViewById(R.id.newsRecyclerView)

        // Initialize the adapter
        // newsAdapter = NewsAdapter(listOf())
        newsAdapter = NewsAdapter(mutableListOf())

    //    recyclerView.adapter = newsAdapter
        binding.newsRecyclerView.adapter = newsAdapter

        // Observe the LiveData from the ViewModel
        newsViewModel.newsData.observe(
            this,
            Observer { response ->
                // Here we update the articles in the adapter, not in the activity
                newsAdapter.updateArticles(response.articles)
            }
        )

        // Fetch the news data
        newsViewModel.fetchNewsData(1)
    }
}
