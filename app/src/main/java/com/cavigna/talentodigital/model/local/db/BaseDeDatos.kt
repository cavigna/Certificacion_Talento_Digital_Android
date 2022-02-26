package com.cavigna.talentodigital.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.model.models.CoinDetails


@Database(entities = [Coin::class, CoinDetails::class], version = 1, exportSchema = false)
abstract class BaseDeDatos : RoomDatabase() {
    abstract val dao: CoinDao
}