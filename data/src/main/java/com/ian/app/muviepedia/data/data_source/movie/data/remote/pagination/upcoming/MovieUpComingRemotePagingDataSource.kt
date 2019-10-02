package com.ian.app.muviepedia.data.data_source.movie.data.remote.pagination.upcoming

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.data.data.ResultToConsume
import com.ian.app.muviepedia.data.data_source.movie.data.local.dao.MovieUpComingPaginationDao
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MovieUpComingPaginationData
import com.ian.app.muviepedia.data.data_source.movie.data.remote.MovieRemoteDataSource
import com.ian.app.muviepedia.data.data_source.movie.data.remote.toPaginationUpComingMovie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class MovieUpComingRemotePagingDataSource(private val dataSource: MovieRemoteDataSource,
                                          private val dao: MovieUpComingPaginationDao,
                                          private val scope: CoroutineScope) : PageKeyedDataSource<Int, MovieUpComingPaginationData>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieUpComingPaginationData>) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUpComingPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUpComingPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<MovieUpComingPaginationData>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.getPaginationUpComingMovie(page)
            if (response.status == ResultToConsume.Status.SUCCESS) {
                val result = response.data!!.results.toPaginationUpComingMovie(scope)
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