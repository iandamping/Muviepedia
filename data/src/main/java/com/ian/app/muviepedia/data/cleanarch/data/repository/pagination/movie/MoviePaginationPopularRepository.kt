package com.ian.app.muviepedia.data.cleanarch.data.repository.pagination.movie

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularPaginationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MoviePaginationPopularRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieCacheDataSource
) : PageKeyedDataSource<Int, MovieLocalPopularPaginationEntity>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieLocalPopularPaginationEntity>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieLocalPopularPaginationEntity>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieLocalPopularPaginationEntity>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<MovieLocalPopularPaginationEntity>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val firstData = remoteDataSource.getRemotePaginationPopularMovie(page)
                checkNotNull(firstData.data) { " ${firstData.message} " }
                assert(firstData.data!!.isNotEmpty())
                localDataSource.setCachePaginationPopularMovie(firstData.data!!)
                callback(firstData.data!!)
            } catch (e: Exception) {
                logE(e.message!!)
            }
        }

    }
}