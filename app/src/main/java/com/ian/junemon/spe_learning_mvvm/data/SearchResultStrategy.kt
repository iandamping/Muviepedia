package com.ian.junemon.spe_learning_mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 *
Created by Ian Damping on 23/09/2019.
Github = https://github.com/iandamping

This is not SSOT strategy, this is using cold observeable <Coroutine Flow> to search data
 */

private fun searchKeywordFlow(data: String): Flow<String> = flow {
    if (data != "") emit(data)
}

private fun <T> resultOfFlow(data: ResultToConsume<T>): Flow<ResultToConsume<T>> = flow {
    emit(ResultToConsume.loading())
    if (data.status == ResultToConsume.Status.SUCCESS) {
        emit(ResultToConsume.success(data.data!!))
    } else if (data.status == ResultToConsume.Status.ERROR) {
        emit(ResultToConsume.error(data.message!!))
    }
}

@ExperimentalCoroutinesApi
@FlowPreview
fun <T> searchResultLiveData(data: String, networkCall: suspend (querry: String) -> ResultToConsume<T>): LiveData<ResultToConsume<T>> = liveData {
    searchKeywordFlow(data).debounce(200L).buffer().map { networkCall.invoke(it) }
            .flatMapLatest { resultFlow -> resultOfFlow(resultFlow) }.catch { emit(ResultToConsume.error(it.message!!)) }
            .collectLatest { resultFlow -> emit(resultFlow) }

}