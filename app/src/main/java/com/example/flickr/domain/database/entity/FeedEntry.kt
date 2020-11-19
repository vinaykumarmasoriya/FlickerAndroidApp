package com.example.flickr.domain.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.flickr.domain.database.FeedConverter
import com.example.flickr.domain.network.model.Feed

@Entity(indices = arrayOf(Index(value = ["tag"], unique = true)))
data class FeedEntry(
    @PrimaryKey(autoGenerate = false)
    var link: String = "",
    var tag: String = "",
    @TypeConverters(FeedConverter::class)
    var feeds: List<Feed> = listOf()
)
