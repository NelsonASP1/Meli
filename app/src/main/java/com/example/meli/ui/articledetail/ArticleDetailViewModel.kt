package com.example.meli.ui.articledetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.meli.data.entities.DataItem
import com.example.meli.data.repository.ArticleRepository
import com.example.meli.utils.Resource

class ArticleDetailViewModel @ViewModelInject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<DataItem>> = _character


    fun start(id: String) {
        _id.value = id
    }

}
