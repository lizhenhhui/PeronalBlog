package com.example.personalblog

import android.app.Application
import android.content.Context

class PersonalBlogApplication : Application() {
    companion object{
      lateinit  var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}