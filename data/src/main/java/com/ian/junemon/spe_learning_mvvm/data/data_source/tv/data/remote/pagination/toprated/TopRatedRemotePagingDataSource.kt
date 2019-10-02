package com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.remote.pagination.toprated

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.junemon.spe_learning_mvvm.data.data.ResultToConsume
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.dao.TvTopRatedPaginationDao
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvTopRatedPaginationData
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.remote.TvRemoteDataSource
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.remote.toPaginationTopRatedTv
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TopRatedRemotePagingDataSource(private val dataSource: TvRemoteDataSource,
                                     private val dao: TvTopRatedPaginationDao,
                                     private val scope: CoroutineScope) : PageKeyedDataSource<Int, TvTopRatedPaginationData>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TvTopRatedPaginationData>) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvTopRatedPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvTopRatedPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<TvTopRatedPaginationData>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.getPaginationGetTopRatedTv(page)
            if (response.status == ResultToConsume.Status.SUCCESS) {
                val result = response.data!!.results.toPaginationTopRatedTv(scope)
                dao.insertAll(result)
                callback(result)
            } else if (response.status == ResultToConsume.Status.ERROR) {
                logE(response.message!!)
            }

        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        logE(e.message ?: e.toString())
    }
}