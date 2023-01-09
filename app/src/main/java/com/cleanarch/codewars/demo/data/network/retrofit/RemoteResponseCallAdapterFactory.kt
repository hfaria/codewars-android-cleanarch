package com.cleanarch.codewars.demo.data.network.retrofit

import com.cleanarch.codewars.demo.data.network.response.NetworkResponse
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RemoteResponseCallAdapterFactory : Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != NetworkResponse::class.java) {
            return null
        }
        val bodyType = getParameterUpperBound(0, returnType as ParameterizedType)
        return RemoteResponseCallAdapter<Any>(bodyType)
    }
}
