package com.ksb.automatednewsapp.ui.adapters


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.BlurTransformation
import com.ksb.automatednewsapp.ui.NewsDetailActivity
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

        @BindingAdapter("watchOnYoutube")
        @JvmStatic
        fun watchOnYoutube(layout: ConstraintLayout, titleList: List<String>) {
            if (titleList.isNotEmpty()) {

                layout.setOnClickListener {
                    val url = "https://www.youtube.com/channel/UCIlR9h2v5lgCrNjt96xsCRg/videos"

                    var intent: Intent? = null
                    try {
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.setPackage("com.google.android.youtube")
                        intent.data = Uri.parse(url)
                        layout.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        layout.context.startActivity(intent)
                    }
                }


            }
        }


    }
}