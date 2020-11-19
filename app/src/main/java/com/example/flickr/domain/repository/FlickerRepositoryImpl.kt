package com.example.flickr.domain.repository

import androidx.lifecycle.LiveData
import com.example.flickr.domain.database.dao.FeedDao
import com.example.flickr.domain.database.entity.FeedEntry
import com.example.flickr.domain.datasource.FlickerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlickerRepositoryImpl(
    private val feedDao: FeedDao,
    private val dataSource: FlickerDataSource
) : FlickerRepository {

    init {
        dataSource.apply {
            downloadedFeeds.observeForever { data ->
                persistFetchedFeed(data)
            }
        }
    }

    override suspend fun loadFeed(tag: String) {
        dataSource.fetchFeedsData(tag)
    }

    override suspend fun getFeedByTag(tag: String): LiveData<FeedEntry> {
        return withContext(Dispatchers.IO) {
            return@withContext feedDao.getFeedByTag(tag)
        }
    }

    private fun persistFetchedFeed(feeds: FeedEntry) {
        GlobalScope.launch(Dispatchers.IO) {
            feedDao.deleteAll(feeds.tag)
            feedDao.insertFeeds(feeds)
        }
    }

}