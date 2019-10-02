package com.ian.app.muviepedia.data.data_source.movie.data.remote.pagination.popular

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.data.data.ResultToConsume
import com.ian.app.muviepedia.data.data_source.movie.data.local.dao.MoviePopularPaginationDao
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MoviePopularPaginationData
import com.ian.app.muviepedia.data.data_source.movie.data.remote.MovieRemoteDataSource
import com.ian.app.muviepedia.data.data_source.movie.data.remote.toPaginationPopularMovie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 23/09/2019.
Github = https://github.com/iandamping
 */
class MoviePopularRemotePagingDataSource(
        private val dataSource: MovieRemoteDataSource,
        private val dao: MoviePopularPaginationDao,
        private val scope: CoroutineScope) : PageKeyedDataSource<Int, MoviePopularPaginationData>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MoviePopularPaginationData>) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoviePopularPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoviePopularPaginationData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }


    private fun fetchData(page: Int, callback: (List<MoviePopularPaginationData>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.getPaginationPopularMovie(page)
            if (response.status == ResultToConsume.Status.SUCCESS) {
                val result = response.data!!.results.toPaginationPopularMovie(scope)
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