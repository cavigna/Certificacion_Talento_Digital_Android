package com.cavigna.talentodigital.model.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.model.models.CoinDetails


@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfCoins(listOfCoins: List<Coin>)

    @Query("SELECT * FROM coins_table")
    suspend fun selectAllCoins(): List<Coin>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetails(coinDetails: CoinDetails)

    @Query("SELECT * FROM coin_details_table WHERE id=:id")
    suspend fun selectCoinDetails(id: String): CoinDetails

}