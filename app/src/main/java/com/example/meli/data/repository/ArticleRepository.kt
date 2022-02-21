package com.example.meli.data.repository

import com.example.meli.data.local.ArticleDao
import com.example.meli.data.remote.ArticleRemoteDataSource
import com.example.meli.utils.performGetOperation
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val remoteDataSource: ArticleRemoteDataSource,
    private val localDataSource: ArticleDao
) {

    fun getCharacter(id: String) = performGetOperation(
        databaseQuery = { localDataSource.getItemArticles(id) },
        networkCall = { remoteDataSource.getItems(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllArticlesItems() },
        networkCall = { remoteDataSource.getArticle() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )


}