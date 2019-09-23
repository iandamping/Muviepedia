package com.ian.junemon.spe_learning_mvvm.movie.data.remote

import com.ian.app.helper.util.logE
import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalData
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.nowPlayingMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.upcomingMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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


fun List<MovieData>.toUpComingMovie(): MutableList<MovieLocalData> {
    return this.map {
        MovieLocalData(it.id, upcomingMovie, it.title, imageFormatter + it.poster_path)
    }.toMutableList()
}

fun List<MovieData>.toNowPlayingMovie(): MutableList<MovieLocalData> {
    return this.map {
        MovieLocalData(it.id, nowPlayingMovie, it.title, imageFormatter + it.poster_path)
    }.toMutableList()
}

fun List<MovieData>.toPopularMovie(): MutableList<MovieLocalData> {
    return this.map {
        MovieLocalData(it.id, popularMovie, it.title, imageFormatter + it.poster_path)
    }.toMutableList()
}
