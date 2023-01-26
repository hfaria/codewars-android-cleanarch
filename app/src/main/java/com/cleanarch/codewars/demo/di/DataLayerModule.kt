package com.cleanarch.codewars.demo.di

import android.app.Application
import androidx.room.Room
import com.cleanarch.codewars.demo.data.network.retrofit.CODEWARS_ENDPOINT
import com.cleanarch.codewars.demo.data.network.retrofit.CodeWarsApi
import com.cleanarch.codewars.demo.data.network.retrofit.RemoteResponseCallAdapterFactory
import com.cleanarch.codewars.demo.data.repository.db.dao.UserDao
import com.cleanarch.codewars.demo.data.repository.db.db.AppDatabase
import com.cleanarch.codewars.demo.data.repository.db.db.DATABASE_NAME
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

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }
}
