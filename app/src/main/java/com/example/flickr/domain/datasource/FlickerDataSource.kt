package com.example.flickr.domain.datasource

import androidx.lifecycle.LiveData
import com.example.flickr.domain.database.entity.FeedEntry

interface FlickerDataSource {
    val downloadedFeeds: LiveData<FeedEntry>
    suspend fun fetchFeedsData(tag: String)
}