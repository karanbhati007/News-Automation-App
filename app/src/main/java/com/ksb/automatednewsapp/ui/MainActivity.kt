package com.ksb.automatednewsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ksb.automatednewsapp.R
import com.ksb.automatednewsapp.databinding.ActivityMainBinding
import com.ksb.automatednewsapp.model.News
import com.ksb.automatednewsapp.utils.NetworkResult
import com.ksb.automatednewsapp.ui.viewmodels.MainViewModel
import com.ksb.automatednewsapp.ui.viewpager.ViewPagerAdapter
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        vertical_viewpager.adapter = ViewPagerAdapter(
            this, News(
                emptyList(), emptyList(), emptyList(),
                emptyList()
            )
        )
        requestNews()


    }



    private fun requestNews() {
        mainViewModel.getNews()
        mainViewModel.newsResponse.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    // Got it !!
                    progressBar.visibility = View.GONE
                    vertical_viewpager.visibility = View.VISIBLE
                    val news: News = response.data!!
                    vertical_viewpager.adapter = ViewPagerAdapter(this, news)

                    Log.i(TAG, "requestNews: " + response.data.toString())

                }
                is NetworkResult.Loading -> {
                    // Showing Progress Bar
                    Log.i(TAG, "Loading: ")
                }
                is NetworkResult.Error -> {
                    Log.i(TAG, "Error: " + response.message)

                    Snackbar.make(vertical_viewpager.rootView, response.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        })
    }

}