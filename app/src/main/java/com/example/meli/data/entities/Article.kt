package com.example.meli.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Article(
    @PrimaryKey
    val id: String,

    val title: String,

    val price: String,

    val condition: String,

    @SerializedName("thumbnail")
    val image: String
)


