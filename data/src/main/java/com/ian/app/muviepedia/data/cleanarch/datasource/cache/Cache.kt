package com.ian.app.muviepedia.data.cleanarch.datasource.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ian.app.muviepedia.model.ResultToConsume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

/**
 * Created by Ian Damping on 05,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

fun <T, A> ssotResultLiveDatas(
    databaseQuery: () -> Flow<T>,
    networkCall: suspend () -> ResultToConsume<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ResultToConsume<T>> =
    liveData(Dispatchers.IO) {
        // emit loading
        val disposables = emitSource(databaseQuery.invoke().map {
            ResultToConsume.loading(it)
        }.asLiveData())

        try {
            val responseStatus = networkCall.invoke()
            disposables.dispose()
            check(responseStatus.status == ResultToConsume.Status.SUCCESS) {
                " ${responseStatus.message} "
            }
            assert(responseStatus.data != null) {
                " data is null "
            }
            saveCallResult(responseStatus.data!!)
            emitSource(databaseQuery.invoke().map { ResultToConsume.success(it) }.asLiveData())
        } catch (e: Exception) {
            emitSource(databaseQuery.invoke().map { ResultToConsume.error(e.message!!, it) }.asLiveData())
        }
    }
