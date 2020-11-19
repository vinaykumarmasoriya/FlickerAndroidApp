package com.example.flickr.domain.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickr.domain.database.entity.FeedEntry

@Dao
interface FeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeeds(feed: FeedEntry): Long

    @Query("SELECT * FROM FeedEntry WHERE tag =:tag")
    fun getFeedByTag(tag: String): LiveData<FeedEntry>

    @Query("DELETE FROM FeedEntry WHERE tag =:tag")
    fun deleteAll(tag: String)
}