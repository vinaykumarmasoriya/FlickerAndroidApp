package com.example.flickr.domain.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.flickr.domain.database.entity.FeedEntry
import com.example.flickr.domain.network.FlickerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class FlickerDataSourceImpl(
    private val flickerService: FlickerService
) : FlickerDataSource {

    private val _downloadedFeeds = MediatorLiveData<FeedEntry>()
    override val downloadedFeeds: LiveData<FeedEntry>
        get() = _downloadedFeeds

    override suspend fun fetchFeedsData(tag: String) {
        withContext(Dispatchers.IO) {
            try {
                val fetchedData = flickerService.getFeedsAsync(tag, 1, 100, "json").await()
                val feedEntry = FeedEntry(tag = tag)
                feedEntry.feeds = fetchedData.feeds
                fetchedData.feeds.forEach {
                    feedEntry.link = it.link
                }
                Log.d("FeedEntry", "*****"+feedEntry.toString())
                _downloadedFeeds.postValue(feedEntry)
            } catch (e: Exception) {
                Log.d("Error", "******" + e.message)
            }
        }
    }
}