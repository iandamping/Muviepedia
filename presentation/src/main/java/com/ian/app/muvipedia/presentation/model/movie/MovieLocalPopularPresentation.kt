package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalPopularData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalPopularPresentation(var id: Int?, var title: String?, var poster_path: String?)

fun MovieLocalPopularData.mapToPresentation(): MovieLocalPopularPresentation =
    MovieLocalPopularPresentation(
        id,
        title,
        poster_path
    )

fun List<MovieLocalPopularData>.mapToPresentation(): List<MovieLocalPopularPresentation> = map { it.mapToPresentation() }