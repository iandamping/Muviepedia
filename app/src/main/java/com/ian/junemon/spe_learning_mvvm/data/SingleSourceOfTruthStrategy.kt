package com.ian.junemon.spe_learning_mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 *
Created by Ian Damping on 19/09/2019.
Github = https://github.com/iandamping

 Class ini berfungsi untuk melakukan SSOT
 */



fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> ResultToConsume<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<ResultToConsume<T>> =
        liveData(Dispatchers.IO) {
            //emit loading
            emit(ResultToConsume.loading())

            //emit livedata from database
            val source = databaseQuery.invoke().map {
                ResultToConsume.success(it)
            }
            emitSource(source)

            val responseStatus = networkCall.invoke()

            if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                emit(ResultToConsume.error(responseStatus.message!!))
                emitSource(source)
            }
        }