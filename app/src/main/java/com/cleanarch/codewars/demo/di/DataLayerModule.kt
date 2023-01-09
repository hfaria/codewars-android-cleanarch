package com.cleanarch.codewars.demo.di

import com.cleanarch.codewars.demo.data.network.retrofit.CODEWARS_ENDPOINT
import com.cleanarch.codewars.demo.data.network.retrofit.CodeWarsApi
import com.cleanarch.codewars.demo.data.network.retrofit.RemoteResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataLayerModule {
    @Singleton
    @Provides
    fun provideCodeWarsApi(): CodeWarsApi {
        return Retrofit.Builder()
            .baseUrl(CODEWARS_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RemoteResponseCallAdapterFactory())
            .build()
            .create(CodeWarsApi::class.java)
    }
}
