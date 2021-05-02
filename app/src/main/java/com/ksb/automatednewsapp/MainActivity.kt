package com.ksb.automatednewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ksb.automatednewsapp.databinding.ActivityMainBinding
import com.ksb.automatednewsapp.viewpager.ViewPagerAdapter
import com.ksb.automatednewsapp.model.News
import com.ksb.automatednewsapp.util.NetworkResult
import com.ksb.automatednewsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        vertical_viewpager.adapter = ViewPagerAdapter(this, News(emptyList(), emptyList(), emptyList(),
            emptyList()))
        requestNews()


    }



    private fun requestNews() {
        mainViewModel.getNews()
        mainViewModel.newsResponse.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    // Got it !!
                    val news:News = response.data!!
                    vertical_viewpager.adapter = ViewPagerAdapter(this, news)

                    Log.i(TAG, "requestNews: " + response.data.toString())

                }
                is NetworkResult.Loading -> {
                    // Show progress Bar
                    Log.i(TAG, "Loading: ")
                }
                is NetworkResult.Error -> {
                    Log.i(TAG, "Error: "+response.message)
                }
            }

        })
    }

}