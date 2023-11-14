package com.balanmaharaja.zusers.module.repo

import com.balanmaharaja.zusers.module.model.ResponseListUsers
import network.ApiService
import network.Result
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UserRepository {

    suspend fun getUsers(): Result<ResponseListUsers> {
        // Make a network request to get the user from the API.

        val BASE_URL = "https://reqres.in"

        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


        val mOkHttpClient = OkHttpClient
            .Builder()
            .addNetworkInterceptor(mHttpLoggingInterceptor)
            .build()


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()


        return try {

            val response = retrofit.create(ApiService::class.java).getAllUsers()

            if (response.isSuccessful) {

                Result.Success(response.body()!!)
            }
            else {

                Result.Failure(response.message())
            }
        } catch (error: Exception) {

            val exception = when (error)
            {
                is SocketException,
                is UnknownHostException -> "No Internet"
                is SocketTimeoutException -> "Timeout Error"

                else -> "Something went wrong"
            }

            Result.Failure(exception)
        }
    }
}
