package com.ian.app.muviepedia.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.ian.app.muviepedia.model.ResultToConsume
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 *
Created by Ian Damping on 23/09/2019.
Github = https://github.com/iandamping

This is now consume as SSOT strategy, using cold observeable <Coroutine Flow> to search data
 */

private fun searchKeywordFlow(data: String): Flow<String> = flow {
    if (data != "") emit(data)
}

@ExperimentalCoroutinesApi
@FlowPreview
fun <T, A> searchResultLiveData(
    data: String,
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend (querry: String) -> ResultToConsume<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ResultToConsume<T>> = liveData {
    // emit loading with cache data if exist
    val disposables = emitSource(databaseQuery.invoke().map {
        ResultToConsume.loading(it)
    })

    try {
        searchKeywordFlow(data).debounce(200L).buffer().map { networkCall.invoke(it) }
            .distinctUntilChanged()
            .collectLatest { resultFlow ->
                disposables.dispose()
                checkNotNull(resultFlow) {
                    " result from network call is null"
                }
                check(resultFlow.status == ResultToConsume.Status.SUCCESS) {
                    " result status is not success "
                }
                assert(resultFlow.data != null) {
                    " data from result is null"
                }
                saveCallResult(resultFlow.data!!)
                emitSource(databaseQuery.invoke().map { ResultToConsume.success(it) })
            }
    } catch (e: Exception) {
        emitSource(databaseQuery.invoke().map { ResultToConsume.error(e.message!!, it) })
    }
}

/*
Old style
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

}*/
