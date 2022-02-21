package com.example.meli.data.remote

import com.example.meli.data.entities.ArticleList
import com.example.meli.data.entities.DataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {


    @GET("sites/MLA/search?q=tecnologia")
    suspend fun getAllArticle() : Response<ArticleList>

   // @GET("search?")
   // suspend fun getItems(@Query("q") item: String): Response<CharacterList>

    @GET("items/{id}")
    suspend fun getItem(@Path("id") id: String): Response<DataItem>
}