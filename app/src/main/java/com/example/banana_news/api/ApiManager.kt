package com.example.banana_news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManager {
    companion object{
        private var retrofit:Retrofit?= null

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit= Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
        fun getApi():webServices{

            return getInstance().create(webServices::class.java)

        }



    }
}