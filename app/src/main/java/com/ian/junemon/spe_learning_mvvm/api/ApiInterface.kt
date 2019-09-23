package com.ian.junemon.spe_learning_mvvm.api

import com.ian.junemon.spe_learning_mvvm.BuildConfig.*
import com.ian.junemon.spe_learning_mvvm.model.DetailMovieData
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieData
import retrofit2.Response
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
    //level 1
    @GET(popularMovie)
    suspend fun getPopularMovieResponse(@Query("api_key") apiKey: String): Response<ResultResponse<MovieData>>

    @GET(nowPlayingMovie)
    suspend fun getNowPlayingMovieResponse(@Query("api_key") apiKey: String): Response<ResultResponse<MovieData>>

    @GET(upComingMovie)
    suspend fun getUpComingMovieResponse(@Query("api_key") apiKey: String): Response<ResultResponse<MovieData>>

    @GET("$detailMovie{movie}")
    suspend fun getDetailMovieResponse(@Path("movie") movieId: Int, @Query("api_key") apiKey: String): Response<DetailMovieData>

    @GET("$similarMovie{movie_id}/similar")
    suspend fun getSimilarMovieResponse(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Response<ResultResponse<MovieData>>

    @GET(searchMovie)
    suspend fun getSearchMovieResponse(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): Response<ResultResponse<MovieData>>


    /*Movie Paging session*/
    @GET(popularMovie)
    suspend fun pagingGetPopularMovieResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<MovieData>>

    @GET(nowPlayingMovie)
    suspend fun pagingGetNowPlayingMovieMovieResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<MovieData>>

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<MovieData>>


    //level 0
    @GET(popularMovie)
    suspend fun getPopularMovieAsync(@Query("api_key") apiKey: String): ResultResponse<MovieData>

    @GET(nowPlayingMovie)
    suspend fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): ResultResponse<MovieData>

    @GET(upComingMovie)
    suspend fun getUpComingMovieAsync(@Query("api_key") apiKey: String): ResultResponse<MovieData>

    @GET("$detailMovie{movie}")
    suspend fun getDetailMovieAsync(@Path("movie") movieId: Int, @Query("api_key") apiKey: String): DetailMovieData

    @GET(searchMovie)
    suspend fun getSearchMovieAsync(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): ResultResponse<MovieData>

    @GET("$similarMovie{movie_id}/similar")
    suspend fun getSimilarMovieAsync(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): ResultResponse<MovieData>

    /*Movie Paging session*/
    @GET(popularMovie)
    suspend fun pagingGetPopularMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): ResultResponse<MovieData>

    @GET(nowPlayingMovie)
    suspend fun pagingGetNowPlayingMovieMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): ResultResponse<MovieData>

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieAsync(@Query("api_key") apiKey: String, @Query("page") page: Int): ResultResponse<MovieData>

}