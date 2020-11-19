package com.example.flickr.domain.network.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("m")
    var imageUrl: String = ""
)