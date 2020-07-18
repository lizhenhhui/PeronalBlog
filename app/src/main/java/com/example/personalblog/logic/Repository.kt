package com.example.personalblog.logic

import androidx.lifecycle.liveData
import com.example.personalblog.logic.model.LoginRequest
import com.example.personalblog.logic.model.RegisterRequest
import com.example.personalblog.logic.network.PersonalBlogNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {
    fun getAllArticle()= fire(Dispatchers.IO){
        val articleLists=PersonalBlogNetWork.getAllArticle()
        if(articleLists!=null){
            Result.success(articleLists)
        }else{
            Result.failure(RuntimeException("getAllResponse response is null"))
        }
    }
    fun login(loginRequest: LoginRequest)= fire(Dispatchers.IO){
        val loginMsg=PersonalBlogNetWork.login(loginRequest)
        if(loginMsg.position){
            Result.success(loginMsg)
        }else{
            Result.failure(RuntimeException("loginMsg response is ${loginMsg.position}"))
        }
    }
    fun register(registerRequest: RegisterRequest)= fire(Dispatchers.IO){
        val registerMsg=PersonalBlogNetWork.register(registerRequest)
        if(registerMsg.position){
            Result.success(registerMsg)
        }else{
            Result.failure(RuntimeException("registerMsg response is ${registerMsg.position}"))
        }
    }
    private fun<T> fire(context: CoroutineContext, block:suspend ()->Result<T>)= liveData<Result<T>> (context)
    {
        val result=try {
            block()
        }catch (e: Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }
}