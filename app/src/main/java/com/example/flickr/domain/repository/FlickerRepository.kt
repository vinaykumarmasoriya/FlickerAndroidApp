package com.example.flickr.domain.repository

import androidx.lifecycle.LiveData
import com.example.flickr.domain.database.entity.FeedEntry

interface FlickerRepository {
    suspend fun loadFeed(tag:String)
    suspend fun getFeedByTag(tag:String):LiveData<FeedEntry>
}