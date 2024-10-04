package com.example.pokephotosapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokephotosapp.model.PokePhoto
import com.example.pokephotosapp.network.PokeApi


import kotlinx.coroutines.launch
import java.io.IOException


sealed interface PokeUiState{
    data class Success(val photos:List<PokePhoto>) : PokeUiState
    object Error: PokeUiState
    object Loading: PokeUiState
}
class PokeViewModel:ViewModel(){
    var pokeUiState:PokeUiState  by mutableStateOf(PokeUiState.Loading)
        private set


    init{
        getPokePhotos()
    }


    private  fun getPokePhotos(){
        viewModelScope.launch {
            pokeUiState = try {
                val listResult = PokeApi.retrofitService.getPhotos()
                PokeUiState.Success(listResult)
            } catch (e: IOException){
                PokeUiState.Error
            }


        }
    }


}
