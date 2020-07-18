package com.example.personalblog.logic.network

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request


class HttpGetData (){

companion object{
    @JvmStatic
    val client=OkHttpClient()
    @JvmStatic
    var data:String?=null;
    @JvmStatic
    fun getData(response: FormBody,url:String): String? {
        val request= Request.Builder()
            .url(url)
            .post(response)
            .build()

       val thread= Thread{
            data= client.newCall(request).execute().body?.string()
        }
        thread.start()
        thread.join()
        return data;
    }
}

}