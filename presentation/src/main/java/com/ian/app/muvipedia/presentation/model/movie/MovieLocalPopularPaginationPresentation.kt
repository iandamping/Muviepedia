package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalPopularPaginationData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalPopularPaginationPresentation(var id: Int?, var title: String?, var poster_path: String?)

fun MovieLocalPopularPaginationData.mapToPresentation(): MovieLocalPopularPaginationPresentation =
    MovieLocalPopularPaginationPresentation(
        id,
        title,
        poster_path
    )

fun List<MovieLocalPopularPaginationData>.mapToPresentation(): List<MovieLocalPopularPaginationPresentation> = map { it.mapToPresentation() }