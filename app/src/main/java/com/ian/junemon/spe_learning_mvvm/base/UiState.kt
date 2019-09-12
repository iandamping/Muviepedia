package com.ian.junemon.spe_learning_mvvm.base

/**
 *
Created by Ian Damping on 28/08/2019.
Github = https://github.com/iandamping
 */
sealed class UiState
object NoData : UiState()
object HasData : UiState()
object Loading : UiState()
class OnError(val msg: String?) : UiState()
