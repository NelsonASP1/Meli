package com.example.meli.ui.articles

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.meli.data.repository.ArticleRepository

class ArticleViewModel @ViewModelInject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    val characters = repository.getCharacters()

    fun searchGif(query: String?) {

    }

}
