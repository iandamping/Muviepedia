package com.ian.app.muviepedia.data.data_source.movie.data.remote

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig.imageFormatter
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class MovieData(
        @field:SerializedName("vote_count")var vote_count: Int?,
        @field:SerializedName("id") var id: Int?,
        @field:SerializedName("video")var video: Boolean?,
        @field:SerializedName("vote_average")var vote_average: Double?,
        @field:SerializedName("title")var title: String?,
        @field:SerializedName("popularity")var popularity: Double?,
        @field:SerializedName("poster_path")var poster_path: String?,
        @field:SerializedName("original_language")var original_language: String?,
        @field:SerializedName("original_title")var original_title: String?,
        @field:SerializedName("genre_ids")var genre_ids: List<Int>?,
        @field:SerializedName("backdrop_path")var backdrop_path: String?,
        @field:SerializedName("adult")var adult: Boolean?,
        @field:SerializedName("overview")var overview: String?,
        @field:SerializedName("release_date")var release_date: String?
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

