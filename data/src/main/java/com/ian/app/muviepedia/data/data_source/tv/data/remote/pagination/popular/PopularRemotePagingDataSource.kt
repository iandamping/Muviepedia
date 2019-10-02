package com.ian.app.muviepedia.data.data_source.tv.data.remote.pagination.popular

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.data.data.ResultToConsume
import com.ian.app.muviepedia.data.data_source.tv.data.local.dao.TvPopularPaginationDao
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.TvPopularPaginationData
import com.ian.app.muviepedia.data.data_source.tv.data.remote.TvRemoteDataSource
import com.ian.app.muviepedia.data.data_source.tv.data.remote.toPaginationPopularTv
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class PopularRemotePagingDataSource(private val dataSource: TvRemoteDataSource,
                                    private val dao: TvPopularPaginationDao,
                                    private val scope: CoroutineScope) : PageKeyedDataSource<Int, TvPopularPaginationData>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TvPopularPaginationData>) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvPopularPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvPopularPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<TvPopularPaginationData>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.getPaginationGetPopularTv(page)
            if (response.status == ResultToConsume.Status.SUCCESS) {
                val result = response.data!!.results.toPaginationPopularTv(scope)
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