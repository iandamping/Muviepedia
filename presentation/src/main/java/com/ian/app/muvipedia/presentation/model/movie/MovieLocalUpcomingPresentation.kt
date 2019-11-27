package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalUpComingData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalUpcomingPresentation(var id: Int?, var title: String?, var poster_path: String?)

fun MovieLocalUpComingData.mapToPresentation(): MovieLocalUpcomingPresentation =
    MovieLocalUpcomingPresentation(
        id,
        title,
        poster_path
    )
fun List<MovieLocalUpComingData>.mapToPresentation(): List<MovieLocalUpcomingPresentation> = map { it.mapToPresentation() }