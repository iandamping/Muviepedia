package com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.paging

import androidx.databinding.ObservableField
import androidx.paging.PageKeyedDataSource
import com.ian.junemon.spe_learning_mvvm.base.UiState
import com.ian.junemon.spe_learning_mvvm.data.repo.movie.MovieRepository
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 *
Created by Ian Damping on 13/09/2019.
Github = https://github.com/iandamping
 */
class UpcomingDataSource(private val repo: MovieRepository, private val scope: CoroutineScope, val state: (ObservableField<UiState>) -> Unit = {}) : PageKeyedDataSource<Int, MovieData>() {
    private val page = 1
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieData>) {
        scope.launch {
            try {
                val result = repo.getUpComingMoviePagingAsync(page).results
                if (result.isNotEmpty()) {
                    callback.onResult(result, null, page + 1)
                }
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieData>) {
        scope.launch {
            try {
                val result = repo.getUpComingMoviePagingAsync(params.key).results
                if (result.isNotEmpty()) {
                    callback.onResult(result, params.key + 1)
                }
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieData>) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        scope.launch {
            try {
                val result = repo.getUpComingMoviePagingAsync(params.key).results
                if (result.isNotEmpty()) {
                    callback.onResult(result, adjacentKey)
                }
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}