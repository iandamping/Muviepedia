package com.ian.junemon.spe_learning_mvvm.api

import com.ian.junemon.spe_learning_mvvm.BuildConfig.*
import com.ian.junemon.spe_learning_mvvm.model.DetailMovieData
import com.ian.junemon.spe_learning_mvvm.model.GenericMovieModel
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
interface ApiInterface {
    /*Movie Session*/
    @GET(popularMovie)
    suspend fun getPopularMovieAsync(@Query("api_key") apiKey: String): GenericMovieModel<MovieData>

    @GET(nowPlayingMovie)
    suspend fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): GenericMovieModel<MovieData>

    @GET(upComingMovie)
    suspend fun getUpComingMovieAsync(@Query("api_key") apiKey: String): GenericMovieModel<MovieData>

    @GET("$detailMovie{movie}")
    suspend fun getDetailMovieAsync(@Path("movie") movieId: Int, @Query("api_key") apiKey: String): DetailMovieData

    @GET(searchMovie)
    suspend fun getSearchMovieAsync(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): GenericMovieModel<MovieData>

    @GET("$similarMovie{movie_id}/similar")
    suspend fun getSimilarMovieAsync(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): GenericMovieModel<MovieData>

    /*Movie Paging session*/
    @GET(popularMovie)
    suspend fun pagingGetPopularMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): GenericMovieModel<MovieData>

    @GET(nowPlayingMovie)
    suspend fun pagingGetNowPlayingMovieMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): GenericMovieModel<MovieData>

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): GenericMovieModel<MovieData>

}