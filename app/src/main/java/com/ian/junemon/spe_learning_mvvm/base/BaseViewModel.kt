package com.ian.junemon.spe_learning_mvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
abstract class BaseViewModel : ViewModel() {
    /*Shared viewModelScope*/
    protected val vmScopes = viewModelScope
    protected val baseUiState:MutableLiveData<UiState> = MutableLiveData()

    protected fun setBaseUiState(state:UiState){
        baseUiState.value = state
    }
}