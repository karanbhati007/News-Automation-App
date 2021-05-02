package com.ksb.automatednewsapp.adapters


import android.content.Intent
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.BlurTransformation
import com.ksb.automatednewsapp.NewsDetailActivity
import com.ksb.automatednewsapp.R



class NewsBinding {
    companion object {


        @BindingAdapter("loadNewsImageFromURL")
        @JvmStatic
        fun loadNewsImageFromURL(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("loadBlurNewsImageFromURL")
        @JvmStatic
        fun loadBlurNewsImageFromURL(imageView: ImageView, imageUrl: String) {

            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
                transformations(BlurTransformation(imageView.context))
            }

        }

        @BindingAdapter("onClickWebOpen")
        @JvmStatic
        fun onClickWebOpen(layout: ConstraintLayout, newsLink: String) {

            layout.setOnClickListener {
                val i = Intent(layout.context, NewsDetailActivity::class.java)
                i.putExtra("url", newsLink)
                layout.context.startActivity(i)
            }

        }


    }
}