package com.example.meli.data.local

import androidx.room.TypeConverter
import com.example.meli.data.entities.Pictures
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.util.*


class MyCustomTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Pictures> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Pictures>>() {

        }.type

        return gson.fromJson<List<Pictures>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Pictures>): String {
        return gson.toJson(someObjects)
    }
}