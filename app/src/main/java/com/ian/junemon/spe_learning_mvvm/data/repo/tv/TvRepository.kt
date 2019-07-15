package com.ian.junemon.spe_learning_mvvm.data.repo.tv

import com.ian.junemon.spe_learning_mvvm.BuildConfig.movieApiKey
import com.ian.junemon.spe_learning_mvvm.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.model.GenericMovieModel
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import com.ian.junemon.spe_learning_mvvm.model.TvData
import io.reactivex.Observable
import kotlinx.coroutines.Deferred

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class TvRepository(private val api: ApiInterface) {

    fun getPopularTvAsync():Deferred<GenericMovieModel<TvData>>{
        return api.getPopularTvAsync(movieApiKey)
    }

    fun getOnAirTvAsync():Deferred<GenericMovieModel<TvData>>{
        return api.getOnAirTvAsync(movieApiKey)
    }

    fun getAiringTodayTvAsync():Deferred<GenericMovieModel<TvData>>{
        return api.getAiringTodayTvAsync(movieApiKey)
    }


}