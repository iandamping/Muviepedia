package com.ian.junemon.spe_learning_mvvm.data.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 *
Created by Ian Damping on 19/09/2019.
Github = https://github.com/iandamping

The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 *
 */


fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> ResultToConsume<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<ResultToConsume<T>> =
        liveData(Dispatchers.IO) {
            //emit loading
            emit(ResultToConsume.loading())

            //emit livedata from database
            val source = databaseQuery.invoke().map {
                //emit succeed from database
                ResultToConsume.success(it)
            }
            //emit it first
            emitSource(source)

            //variable that invoked networkCall function
            val responseStatus = networkCall.invoke()

            if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                //if succeed, save the networkCall data into database
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                //if failed, emit failed and emitsource database livedata
                emit(ResultToConsume.error(responseStatus.message!!))
                emitSource(source)
            }
        }

