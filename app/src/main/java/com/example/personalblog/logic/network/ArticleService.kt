package com.example.personalblog.logic.network

import com.example.personalblog.logic.model.Article
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {
    @GET("article/findAll")
    fun getAll(): Call<ArrayList<Article>>
}