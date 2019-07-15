package com.ian.junemon.spe_learning_mvvm.api

import com.ian.junemon.spe_learning_mvvm.model.*
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.airingTodayTv
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.detailMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.detailTv
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.nowPlayingMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.onAirTv
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularTv
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.searchMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.searchTvShows
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.upComingMovie
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
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
    fun getPopularMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(nowPlayingMovie)
    fun getNowPlayingMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>

    @GET(upComingMovie)
    fun getUpComingMovieAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<MovieData>>


    @GET("$detailMovie{movie}")
    fun getDetailMovieAsync(@Path("movie") movieId: Int, @Query("api_key") apiKey: String): Observable<DetailMovieData>

    @GET(searchMovie)
    fun getSearchMovieAsync(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): Observable<GenericMovieModel<MovieData>>

    /*Tv Session*/

    @GET(airingTodayTv)
    fun getAiringTodayTvAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<TvData>>

    @GET(onAirTv)
     fun getOnAirTvAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<TvData>>

    @GET(popularTv)
     fun getPopularTvAsync(@Query("api_key") apiKey: String): Deferred<GenericMovieModel<TvData>>

    @GET("$detailTv{tv_id}")
    fun getDetailTvAsync(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Observable<DetailTvData>

    @GET(searchTvShows)
    fun getSearchTvShowAsync(@Query("api_key") apiKey: String, @Query("query") searchMovie: String): Observable<GenericMovieModel<MovieData>>

}