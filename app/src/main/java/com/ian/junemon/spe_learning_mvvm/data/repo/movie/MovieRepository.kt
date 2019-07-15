package com.ian.junemon.spe_learning_mvvm.data.repo.movie

import com.ian.junemon.spe_learning_mvvm.BuildConfig.movieApiKey
import com.ian.junemon.spe_learning_mvvm.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.model.GenericMovieModel
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import io.reactivex.Observable
import kotlinx.coroutines.Deferred

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class MovieRepository(private val api: ApiInterface) {

    /*Coroutine way*/
    fun getPopularMovieAsync():Deferred<GenericMovieModel<MovieData>>{
        return api.getPopularMovieAsync(movieApiKey)
    }
}