package com.cavigna.talentodigital.app

import android.content.Context
import androidx.room.Room
import com.cavigna.talentodigital.model.local.db.BaseDeDatos
import com.cavigna.talentodigital.model.remopte.api.ApiService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun providesRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl("https://fake-server-app-crypto.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BaseDeDatos::class.java, "coins.db").build()

    @Singleton
    @Provides
    fun providesCoinDao(db: BaseDeDatos) = db.dao
}