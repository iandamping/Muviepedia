package com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.remote

import com.ian.junemon.spe_learning_mvvm.data.BuildConfig
import com.ian.junemon.spe_learning_mvvm.data.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.data.api.BaseDataSource


/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TvRemoteDataSource(private val api: ApiInterface): BaseDataSource() {
    suspend fun getPopularTv() = getResult { api.getPopularTvResponse(BuildConfig.movieApiKey) }

    suspend fun getTopRatedTv() = getResult { api.getTopRatedTvResponse(BuildConfig.movieApiKey) }

    suspend fun getAiringTodayTv() = getResult { api.getAiringTodayTvResponse(BuildConfig.movieApiKey) }

    suspend fun getDetailTv(movieId: Int) = getResult { api.getDetailTvResponse(movieId, BuildConfig.movieApiKey) }

    suspend fun getSimilarTv(movieId: Int) = getResult { api.getSimilarTvResponse(movieId, BuildConfig.movieApiKey) }

    suspend fun getPaginationGetPopularTv(page: Int) = getResult { api.pagingGetPopularTvResponse(BuildConfig.movieApiKey, page) }

    suspend fun getSearchTv(querry: String) = getResult { api.getSearchTvResponse(BuildConfig.movieApiKey, querry) }

    suspend fun getPaginationGetTopRatedTv(page: Int) = getResult { api.pagingGetTopRatedTvResponse(BuildConfig.movieApiKey, page) }
}