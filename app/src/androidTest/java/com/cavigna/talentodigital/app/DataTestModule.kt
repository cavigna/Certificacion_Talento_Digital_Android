package com.cavigna.talentodigital.app

import android.content.Context
import androidx.room.Room
import com.cavigna.talentodigital.model.local.db.BaseDeDatos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataTestModule {

    @Provides
    @Named("test.db")
    fun provideInMemoryDbTest(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, BaseDeDatos::class.java
        ).allowMainThreadQueries()
            .build()


}
