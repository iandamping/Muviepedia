package com.ian.junemon.spe_learning_mvvm.data.repo.movie

import com.ian.junemon.spe_learning_mvvm.BuildConfig.movieApiKey
import com.ian.junemon.spe_learning_mvvm.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.model.DetailMovieData
import com.ian.junemon.spe_learning_mvvm.model.GenericMovieModel
import com.ian.junemon.spe_learning_mvvm.model.MovieData

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class MovieRepository(private val api: ApiInterface) {

    suspend fun getPopularMovieAsync(): GenericMovieModel<MovieData> {
        return api.getPopularMovieAsync(movieApiKey)
    }

    suspend fun getNowPlayingMovieAsync(): GenericMovieModel<MovieData> {
        return api.getNowPlayingMovieAsync(movieApiKey)
    }

    suspend fun getUpComingMovieAsync(): GenericMovieModel<MovieData> {
        return api.getUpComingMovieAsync(movieApiKey)
    }

    suspend fun getDetailMovieAsync(movieId: Int): DetailMovieData {
        return api.getDetailMovieAsync(movieId, movieApiKey)
    }

    suspend fun getSimilarMovieAsync(movieId: Int): GenericMovieModel<MovieData> {
        return api.getSimilarMovieAsync(movieId, movieApiKey)
    }

    suspend fun getSearchMovieAsync(querry: String): GenericMovieModel<MovieData> {
        return api.getSearchMovieAsync(querry, movieApiKey)
    }

    /*Paging session*/
    suspend fun getNowPlayingMoviePagingAsync(pageMovie: Int): GenericMovieModel<MovieData> {
        return api.pagingGetNowPlayingMovieMovieAsync(movieApiKey, pageMovie)
    }

    suspend fun getPopularMoviePagingAsync(pageMovie: Int): GenericMovieModel<MovieData> {
        return api.pagingGetPopularMovieAsync(movieApiKey, pageMovie)
    }

    suspend fun getUpComingMoviePagingAsync(pageMovie: Int): GenericMovieModel<MovieData> {
        return api.pagingGetUpComingMovieAsync(movieApiKey, pageMovie)
    }
}