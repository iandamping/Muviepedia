package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalNowPlayingData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalNowPlayingPresentation(var id: Int?, var title: String?, var poster_path: String?)

fun MovieLocalNowPlayingData.mapToPresentation(): MovieLocalNowPlayingPresentation =
    MovieLocalNowPlayingPresentation(
        id,
        title,
        poster_path
    )
fun List<MovieLocalNowPlayingData>.mapToPresentation(): List<MovieLocalNowPlayingPresentation> = map { it.mapToPresentation() }