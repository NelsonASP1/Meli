package com.example.meli.data.remote

import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val articleService: ArticleService
): BaseDataSource() {

    suspend fun getArticle() = getResult { articleService.getAllArticle() }
    suspend fun getItems(id: String) = getResult { articleService.getItem(id) }
    //suspend fun getSearch(item: String) = getResult { characterService.getItems(item) }

}