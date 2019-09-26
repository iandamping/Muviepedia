package com.ian.junemon.spe_learning_mvvm.api

import com.ian.junemon.spe_learning_mvvm.BuildConfig.*
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.DetailMovieData
import com.ian.junemon.spe_learning_mvvm.model.DetailTvData
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteData
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

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<MovieData>>



    /*Tv Session*/
    @GET(popularTv)
    suspend fun getPopularTvResponse(@Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteData>>

    @GET(topRatedTv)
    suspend fun getTopRatedTvResponse(@Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteData>>

    @GET(airingTodayTv)
    suspend fun getAiringTodayTvResponse(@Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteData>>

    @GET("$detailTv{tv_id}")
    suspend fun getDetailTvResponse(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Response<DetailTvData>

    @GET("$similarTv{tv_id}/similar")
    suspend fun getSimilarTvResponse(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteData>>

    @GET(searchTvShows)
    suspend fun getSearchTvResponse(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): Response<ResultResponse<TvRemoteData>>

    /*Tv Paging session*/
    @GET(popularTv)
    suspend  fun pagingGetPopularTvResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<TvRemoteData>>

    @GET(topRatedTv)
    suspend fun pagingGetTopRatedTvResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<TvRemoteData>>

}