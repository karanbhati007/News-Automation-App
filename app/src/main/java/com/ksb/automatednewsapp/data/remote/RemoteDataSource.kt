package com.ksb.automatednewsapp.data.remote

import com.ksb.automatednewsapp.model.News
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val newsApi:NewsApi) {

    suspend fun getNews():Response<News>{
        return newsApi.getNews()
    }

}