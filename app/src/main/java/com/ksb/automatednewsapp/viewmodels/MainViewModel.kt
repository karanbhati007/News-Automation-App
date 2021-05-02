package com.ksb.automatednewsapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksb.automatednewsapp.data.Repository
import com.ksb.automatednewsapp.data.remote.NewsApi
import com.ksb.automatednewsapp.model.News
import com.ksb.automatednewsapp.util.NetworkResult
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var newsResponse: MutableLiveData<NetworkResult<News>> = MutableLiveData()

    fun getNews() = viewModelScope.launch {
        getNewsSafeCall()
    }

    private suspend fun getNewsSafeCall() {
        newsResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                val response = repository.remoteDataSource.getNews()
                if (response.isSuccessful) {
                    val news = response.body()
                    newsResponse.value = NetworkResult.Success(news!!)
                } else {
                    newsResponse.value = NetworkResult.Error(response.message())
                }
            } catch (e: Exception) {
                newsResponse.value = NetworkResult.Error("No News Found !!")
            }
        } else {
            newsResponse.value = NetworkResult.Error("No Internet Connection !!")
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}