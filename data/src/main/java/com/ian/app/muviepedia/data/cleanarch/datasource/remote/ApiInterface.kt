package com.ian.app.muviepedia.data.cleanarch.datasource.remote

import com.ian.app.muviepedia.data.BuildConfig.*
import com.ian.app.muviepedia.data.api.ResultResponse
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieRemoteDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieRemoteEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvRemoteDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvRemoteEntity
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
    // level 1
    @GET(popularMovie)
    suspend fun getPopularMovieResponse(@Query("api_key") apiKey: String): Response<ResultResponse<MovieRemoteEntity>>

    @GET(nowPlayingMovie)
    suspend fun getNowPlayingMovieResponse(@Query("api_key") apiKey: String): Response<ResultResponse<MovieRemoteEntity>>

    @GET(upComingMovie)
    suspend fun getUpComingMovieResponse(@Query("api_key") apiKey: String): Response<ResultResponse<MovieRemoteEntity>>

    @GET("$detailMovie{movie}")
    suspend fun getDetailMovieResponse(@Path("movie") movieId: Int, @Query("api_key") apiKey: String): Response<MovieRemoteDetailEntity>

    @GET("$similarMovie{movie_id}/similar")
    suspend fun getSimilarMovieResponse(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Response<ResultResponse<MovieRemoteEntity>>

    @GET(searchMovie)
    suspend fun getSearchMovieResponse(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): Response<ResultResponse<MovieRemoteEntity>>

    /*Movie Paging session*/
    @GET(popularMovie)
    suspend fun pagingGetPopularMovieResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<MovieRemoteEntity>>

    @GET(upComingMovie)
    suspend fun pagingGetUpComingMovieResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<MovieRemoteEntity>>

    /*Tv Session*/
    @GET(popularTv)
    suspend fun getPopularTvResponse(@Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteEntity>>

    @GET(topRatedTv)
    suspend fun getTopRatedTvResponse(@Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteEntity>>

    @GET(airingTodayTv)
    suspend fun getAiringTodayTvResponse(@Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteEntity>>

    @GET("$detailTv{tv_id}")
    suspend fun getDetailTvResponse(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Response<TvRemoteDetailEntity>

    @GET("$similarTv{tv_id}/similar")
    suspend fun getSimilarTvResponse(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Response<ResultResponse<TvRemoteEntity>>

    @GET(searchTvShows)
    suspend fun getSearchTvResponse(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): Response<ResultResponse<TvRemoteEntity>>

    /*Tv Paging session*/
    @GET(popularTv)
    suspend fun pagingGetPopularTvResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<TvRemoteEntity>>

    @GET(topRatedTv)
    suspend fun pagingGetTopRatedTvResponse(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<ResultResponse<TvRemoteEntity>>
}