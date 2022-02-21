package com.example.meli.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meli.data.entities.Article
import com.example.meli.data.entities.DataItem

@Dao
interface ArticleDao {

    @Query("SELECT * FROM characters")
    fun getAllArticlesItems() : LiveData<List<Article>>

    @Query("SELECT * FROM item WHERE id = :id")
    fun getItemArticles(id: String): LiveData<DataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataItem: DataItem)


}