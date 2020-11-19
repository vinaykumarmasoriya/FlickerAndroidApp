package com.example.flickr.domain.network

import com.example.flickr.domain.network.model.FeedResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerService {

    @GET("feeds/photos_public.gne")
    fun getFeedsAsync(
        @Query("tags") tag: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("per_page") itemCount: Int,
        @Query("format") format: String
    ): Deferred<FeedResponse>
}