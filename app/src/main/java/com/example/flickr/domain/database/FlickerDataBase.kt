package com.example.flickr.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flickr.domain.database.dao.FeedDao
import com.example.flickr.domain.database.entity.FeedEntry

@Database(entities = [FeedEntry::class], version = 1)
@TypeConverters(FeedConverter::class)
abstract class FlickerDataBase : RoomDatabase() {
    abstract fun feedDao(): FeedDao

    companion object {
        @Volatile
        private var instance: FlickerDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FlickerDataBase::class.java, "flicker.db"
            ).build()
    }
}