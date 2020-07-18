package com.example.personalblog.ui.Main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.personalblog.logic.Repository
import com.example.personalblog.logic.model.Article

class MainViewModel:ViewModel() {
    private val articleLiveData=MutableLiveData<Any?>()

    val articleList=ArrayList<Article>()

    val getAllResult=Transformations.switchMap(articleLiveData){
        Repository.getAllArticle()
    }

    fun getAll(){
        articleLiveData.value=articleLiveData.value
    }
}