package com.example.flickr.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.flickr.R
import com.example.flickr.core.BaseRecyclerAdapter
import com.example.flickr.domain.network.model.Feed
import kotlinx.android.synthetic.main.activity_detail.view.*

class HomeFeedAdapter : BaseRecyclerAdapter<Feed>(Callback()) {

    class Callback : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.dateTaken.equals(newItem.dateTaken, ignoreCase = true)
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.dateTaken == newItem.dateTaken &&
                    oldItem.media.imageUrl == newItem.media.imageUrl
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_feed_home
    }

}