package com.ksb.automatednewsapp.data.remote

import com.ksb.automatednewsapp.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("/")
    suspend fun getNews():Response<News>
}