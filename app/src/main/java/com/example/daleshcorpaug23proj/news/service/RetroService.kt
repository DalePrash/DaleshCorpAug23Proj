package com.example.daleshcorpaug23proj.news.retrofit
import com.example.daleshcorpaug23proj.news.view.news_articles.ArticleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
interface RetroService {

    @Headers("Accept: application/json")
    @GET("everything")
    suspend fun getNewsData(
        @Query("q") search: String = "sports",
        @Query("apiKey") key: String = "aa67d8d98c8e4ad1b4f16dbd5f3be348",
        @Query("page") page: Int
    ): ArticleResponse
}

object RetroInstance {
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RetroService = retrofit.create(RetroService::class.java)
}
