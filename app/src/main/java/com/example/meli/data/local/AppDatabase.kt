package com.example.meli.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meli.data.entities.Article
import com.example.meli.data.entities.DataItem
import androidx.room.TypeConverters




@Database(entities = [Article::class, DataItem::class], version = 1, exportSchema = false)
@TypeConverters(MyCustomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "dbitem")
                .fallbackToDestructiveMigration()
                .build()
    }

}