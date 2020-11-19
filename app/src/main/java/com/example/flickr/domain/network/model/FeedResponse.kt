package com.example.flickr.domain.network.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("generator")
    var generator: String = "",
    @SerializedName("items")
    var feeds: List<Feed> = listOf(),
    @SerializedName("link")
    var link: String = "",
    @SerializedName("modified")
    var modified: String = "",
    @SerializedName("title")
    var title: String = ""
)