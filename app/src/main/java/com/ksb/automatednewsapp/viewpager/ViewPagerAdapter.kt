package com.ksb.automatednewsapp.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.ksb.automatednewsapp.databinding.ItemContainerBinding
import com.ksb.automatednewsapp.model.News

class ViewPagerAdapter(private val context: Context, private val news: News) : PagerAdapter() {

    //private val mLayoutInflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return news.title.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //val itemView: View =  mLayoutInflater.inflate(R.layout.item_container,container,false)
        //val imageView:ImageView = itemView.findViewById(R.id.image_news)

        val layoutInflater = LayoutInflater.from(container.context)

        val binding = ItemContainerBinding.inflate(layoutInflater, container, false)

        binding.news = news
        binding.position = position

        binding.executePendingBindings() // Check this

        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as ConstraintLayout)
    }
}