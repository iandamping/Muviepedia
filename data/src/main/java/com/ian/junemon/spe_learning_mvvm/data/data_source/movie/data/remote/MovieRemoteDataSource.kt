package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.remote

import com.ian.junemon.spe_learning_mvvm.data.BuildConfig
import com.ian.junemon.spe_learning_mvvm.data.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.data.api.BaseDataSource

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
class MovieRemoteDataSource(private val api: ApiInterface) : BaseDataSource() {

    suspend fun getPopularMovie() = getResult { api.getPopularMovieResponse(BuildConfig.movieApiKey) }

    suspend fun getNowPlayingMovie() = getResult { api.getNowPlayingMovieResponse(BuildConfig.movieApiKey) }

    suspend fun getUpComingMovie() = getResult { api.getUpComingMovieResponse(BuildConfig.movieApiKey) }

    suspend fun getDetailMovie(movieId: Int) = getResult { api.getDetailMovieResponse(movieId, BuildConfig.movieApiKey) }

    suspend fun getSimilarMovie(movieId: Int) = getResult { api.getSimilarMovieResponse(movieId, BuildConfig.movieApiKey) }

    suspend fun getPaginationPopularMovie(page: Int) = getResult { api.pagingGetPopularMovieResponse(BuildConfig.movieApiKey, page) }

    suspend fun getSearchMovie(querry: String) = getResult { api.getSearchMovieResponse(BuildConfig.movieApiKey, querry) }

    suspend fun getPaginationUpComingMovie(page: Int) = getResult { api.pagingGetUpComingMovieResponse(BuildConfig.movieApiKey, page) }
}