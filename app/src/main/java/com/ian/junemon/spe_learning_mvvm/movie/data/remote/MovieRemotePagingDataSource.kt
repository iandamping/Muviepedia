package com.ian.junemon.spe_learning_mvvm.movie.data.remote

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.junemon.spe_learning_mvvm.data.ResultToConsume
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalDao
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalData
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.upcomingMovie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 23/09/2019.
Github = https://github.com/iandamping
 */
class MovieRemotePagingDataSource(private val movieType: String,
                                  private val dataSource: MovieRemoteDataSource,
                                  private val dao: MovieLocalDao,
                                  private val scope: CoroutineScope) : PageKeyedDataSource<Int, MovieLocalData>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieLocalData>) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieLocalData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieLocalData>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }


    private fun fetchData(page: Int, callback: (List<MovieLocalData>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            when (movieType) {
                upcomingMovie -> {
                    val response = dataSource.getPaginationUpComingMovieResponse(page)
                    if (response.status == ResultToConsume.Status.SUCCESS) {
                        val result = response.data!!.results.toUpComingMovie()
                        dao.insertAll(result)
                        callback(result)
                    } else if (response.status == ResultToConsume.Status.ERROR) {
                        logE(response.message!!)
                    }
                }

                popularMovie -> {
                    val response = dataSource.getPaginationPopularMovie(page)
                    if (response.status == ResultToConsume.Status.SUCCESS) {
                        val result = response.data!!.results.toPopularMovie()
                        dao.insertAll(result)
                        callback(result)
                    } else if (response.status == ResultToConsume.Status.ERROR) {
                        logE(response.message!!)
                    }
                }
            }

        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        logE(e.message ?: e.toString())
    }
}