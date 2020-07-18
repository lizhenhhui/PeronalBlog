package com.example.personalblog.logic.network

import com.example.personalblog.logic.model.LoginRequest
import com.example.personalblog.logic.model.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object PersonalBlogNetWork {
    private val userService=ServiceCreator.create(UserService::class.java)

    private val articleService=ServiceCreator.create(ArticleService::class.java)

    suspend fun login(loginRequest: LoginRequest)= userService.login(loginRequest).await()

    suspend fun register(registerRequest: RegisterRequest)= userService.register(registerRequest).await()

    suspend fun getAllArticle()= articleService.getAll().await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if(body!=null)continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
            })
        }
    }
}