package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalUpComingPaginationData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalUpComingPaginationPresentation(var id: Int?, var title: String?, var poster_path: String?)
fun MovieLocalUpComingPaginationData.mapToPresentation(): MovieLocalUpComingPaginationPresentation =
    MovieLocalUpComingPaginationPresentation(
        id,
        title,
        poster_path
    )
fun List<MovieLocalUpComingPaginationData>.mapToPresentation(): List<MovieLocalUpComingPaginationPresentation> = map { it.mapToPresentation() }