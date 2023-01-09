package com.cleanarch.codewars.demo.data.network.retrofit

import com.cleanarch.codewars.demo.data.network.response.NetworkResponse
import com.cleanarch.codewars.demo.data.network.response.GetUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

const val CODEWARS_ENDPOINT = "https://www.codewars.com/api/v1/"

interface CodeWarsApi {

    @GET("users/{uname}")
    fun getUsers(@Path("uname") username: String): NetworkResponse<GetUserResponse>

}