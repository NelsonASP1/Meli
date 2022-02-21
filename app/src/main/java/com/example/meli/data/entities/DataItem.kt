package com.example.meli.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.meli.data.local.MyCustomTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item")
data class DataItem(
    @PrimaryKey
    val id: String,

    @SerializedName("base_price")
    val price: String,

    val title: String,

    val condition: String,

    @SerializedName("accepts_mercadopago")
    val mercadopago: Boolean,

    @SerializedName("sold_quantity")
    val soldQuantity: String,

    @field:TypeConverters(MyCustomTypeConverter::class)
    var pictures: List<Pictures> = listOf()
)
