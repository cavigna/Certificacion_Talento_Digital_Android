package com.cavigna.talentodigital.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cavigna.talentodigital.model.Repositorio
import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.model.models.CoinDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repositorio
): ViewModel() {

    init {
        selectAllCoins()
    }

    private val _listOfCoins: MutableLiveData<List<Coin>> = MutableLiveData(listOf())
    val listOfCoins:LiveData<List<Coin>> = _listOfCoins

    private val _coinDetails: MutableLiveData<CoinDetails> = MutableLiveData(CoinDetails())
    val coinDetails: LiveData<CoinDetails> = _coinDetails

    private fun selectAllCoins(){
        viewModelScope.launch(IO) {
            repo.fetchOrSelectListOfCoins().collect {
                _listOfCoins.postValue(it)
            }
        }
    }

    fun selectOrFetchCoinDetails(id:String){
        viewModelScope.launch(IO) {
            _coinDetails.postValue(CoinDetails())
            repo.fetchOrSelectCoinDetails(id).collect {
                _coinDetails.postValue(it)
            }
        }
    }
}