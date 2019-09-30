package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.remote

import com.ian.junemon.spe_learning_mvvm.data.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class MovieData(
        var vote_count: Int?,
        var id: Int?,
        var video: Boolean?,
        var vote_average: Double?,
        var title: String?,
        var popularity: Double?,
        var poster_path: String?,
        var original_language: String?,
        var original_title: String?,
        var genre_ids: List<Int>?,
        var backdrop_path: String?,
        var adult: Boolean?,
        var overview: String?,
        var release_date: String?
)


suspend fun List<MovieData>.toUpComingMovie(scope: CoroutineScope): MutableList<MovieUpComingLocalData> {
    return withContext(scope.coroutineContext) {
        this@toUpComingMovie.map {
            MovieUpComingLocalData(it.id, it.title, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<MovieData>.toNowPlayingMovie(scope: CoroutineScope): MutableList<MovieNowPlayingLocalData> {
    return withContext(scope.coroutineContext) {
        this@toNowPlayingMovie.map {
            MovieNowPlayingLocalData(it.id, it.title, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<MovieData>.toPopularMovie(scope: CoroutineScope): MutableList<MoviePopularLocalData> {
    return withContext(scope.coroutineContext) {
        this@toPopularMovie.map {
            MoviePopularLocalData(it.id, it.title, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<MovieData>.toPaginationPopularMovie(scope: CoroutineScope): MutableList<MoviePopularPaginationData> {
    return withContext(scope.coroutineContext) {
        this@toPaginationPopularMovie.map {
            MoviePopularPaginationData(it.id, it.title, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<MovieData>.toPaginationUpComingMovie(scope: CoroutineScope): MutableList<MovieUpComingPaginationData> {
    return withContext(scope.coroutineContext) {
        this@toPaginationUpComingMovie.map {
            MovieUpComingPaginationData(it.id, it.title, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<MovieData>.toSearchMovie(scope: CoroutineScope): MutableList<MovieSearchLocalData> {
    return withContext(scope.coroutineContext) {
        this@toSearchMovie.map {
            MovieSearchLocalData(it.id, it.title, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

