package com.example.flickr.domain.network.model


import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.room.Embedded
import com.example.flickr.ui.activity.DATE
import com.example.flickr.ui.activity.DetailActivity
import com.example.flickr.ui.activity.IMAGE
import com.example.flickr.ui.activity.TITLE
import com.example.flickr.utility.Utility
import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("date_taken")
    var dateTaken: String = "",
    @SerializedName("media")
    @Embedded(prefix = "media_")
    var media: Media = Media(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("link")
    var link: String = ""
){
    fun openDetail(view:View){
        val intent = Intent(view.context, DetailActivity::class.java)
        intent.putExtra(TITLE, title)
        intent.putExtra(DATE, dateTaken)
        intent.putExtra(IMAGE, media.imageUrl)
        view.context.startActivity(intent)
    }
}

@BindingAdapter("setDate")
fun date(textView: AppCompatTextView, date: String) {
    textView.text = Utility.getFormattedDate(date)
}