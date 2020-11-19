package com.example.flickr.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.flickr.R
import com.example.flickr.core.BaseActivity
import com.example.flickr.databinding.ActivityDetailBinding
import com.example.flickr.domain.glide.ImageLoader
import com.example.flickr.utility.Utility

const val TITLE ="title"
const val IMAGE = "image"
const val DATE ="date"
class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_detail

    override fun getToolBar(binding: ActivityDetailBinding): Toolbar? {
        return null
    }

    override fun onActivityReady(instance: Bundle?, binding: ActivityDetailBinding) {
        intent.extras?.let {bundle->
            val imageUrl = bundle.getString(IMAGE)
            imageUrl?.let {
                ImageLoader.loadLargeImage(binding.image, it)
            }
            binding.title.text = bundle.getString(TITLE)
            binding.date.text = bundle.getString(DATE)?.let { Utility.getFormattedDate(it) }
            Utility.showMessageIfNoNetwork(binding.container)
        }
    }

}