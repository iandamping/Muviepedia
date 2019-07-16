package com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ian.app.helper.util.retryIO
import com.ian.junemon.spe_learning_mvvm.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.data.repo.movie.MovieRepository
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class MovieViewmodel(private val repo: MovieRepository) : BaseViewModel() {

    init {
        vmScopes.launch {
            retryIO { getPopularMovieAsync() }
        }
    }

    private val _data = MutableLiveData<List<MovieData>>()

    val data: LiveData<List<MovieData>>
        get() = _data

    private suspend fun getPopularMovieAsync() {
        val work1 = repo.getPopularMovieAsync()
        _data.value = work1.await().results
    }

}