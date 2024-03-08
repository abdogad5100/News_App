package com.example.banana_news.api

import com.example.banana_news.model.NewsResponse
import com.example.banana_news.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface webServices {
   @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apikey") apikey:String

   ):Call<SourceResponse>
    @GET("v2/everything")
    fun getNews(
        @Query("apikey") apikey: String,
        @Query("sources") sources:String

    ):Call<NewsResponse>



}