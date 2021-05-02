package com.ksb.automatednewsapp.adapters

import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.BlurTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.ksb.automatednewsapp.R
import jp.wasabeef.blurry.Blurry


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
        fun loadBlurNewsImageFromURL(imageView: ImageView, imageUrl: String){

            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
                transformations(BlurTransformation(imageView.context))
            }

        }


    }
}