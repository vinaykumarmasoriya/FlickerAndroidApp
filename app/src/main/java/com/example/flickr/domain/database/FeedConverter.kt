package com.example.flickr.domain.database

import androidx.room.TypeConverter
import com.example.flickr.domain.network.model.Feed
import com.example.flickr.utility.fromJson
import com.example.flickr.utility.json
import com.google.gson.Gson

class FeedConverter {

    @TypeConverter
    fun convertToString(list: List<Feed>?): String {
        list ?: return ""
        return list.json()
    }

    @TypeConverter
    fun convertToFeeds(value: String?): List<Feed> {
        if (value.isNullOrEmpty()) {
            return arrayListOf()
        }
        return Gson().fromJson<ArrayList<Feed>>(value)
    }
}