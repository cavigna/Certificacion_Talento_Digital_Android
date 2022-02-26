package com.cavigna.talentodigital.model.remopte.api


import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.model.models.CoinDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("general")
    suspend fun fetchListOfCoins():List<Coin>

    @GET("details/{id}")
    suspend fun fetchCoinDetails(
        @Path("id") id:String
    ): CoinDetails

}