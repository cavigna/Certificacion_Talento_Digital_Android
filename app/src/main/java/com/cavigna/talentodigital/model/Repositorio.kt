package com.cavigna.talentodigital.model


import android.util.Log
import com.cavigna.talentodigital.model.local.db.CoinDao
import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.model.models.CoinDetails
import com.cavigna.talentodigital.model.remopte.api.ApiService
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class Repositorio @Inject constructor(
    private val api: ApiService,
    private val dao: CoinDao
) {
    /**
     * Función que controla si la DB contiene información, en caso contrario llama a la api,
     * guarda la info y la emite desde la DB. Cuando está llena la db, no realiza una llamada innecesaria
     * a la API
     */
    suspend fun fetchOrSelectListOfCoins(): Flow<List<Coin>> = flow {
        val coinsDB = dao.selectAllCoins()
        if (coinsDB.isEmpty()){
            val coinsApi = api.fetchListOfCoins()
            dao.insertListOfCoins(coinsApi)
        }
        emit(dao.selectAllCoins())
    }

    /**
     * Al igual que la anterior, solo llama a la API si la información no esta en la DB.
     * Solo consume de la DB
     */
    suspend fun fetchOrSelectCoinDetails(id:String):Flow<CoinDetails> = flow {
        val coinDetailsDB:CoinDetails = dao.selectCoinDetails(id)

        if (flowOf(coinDetailsDB).firstOrNull() == null){
            val coinDetailsApi = api.fetchCoinDetails(id)
            dao.insertCoinDetails(coinDetailsApi)
            Log.v("repositorio", "EMITIDO DE LA API")
            emit(coinDetailsApi)
        }else{
            emit(coinDetailsDB)
            Log.v("repositorio", "EMITIDO DE LA DB")
        }

    }

}