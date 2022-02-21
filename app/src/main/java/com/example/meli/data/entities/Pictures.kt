package com.example.meli.data.entities

import androidx.room.PrimaryKey

data class Pictures(
    @PrimaryKey
    val id: String,
    val url: String,
    val secure_url: String,
    val size: String,
    val max_size: String
)