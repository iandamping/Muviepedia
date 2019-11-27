package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalSearchData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalSearchPresentation(var id: Int?, var title: String?, var poster_path: String?)

fun MovieLocalSearchData.mapToPresentation(): MovieLocalSearchPresentation =
    MovieLocalSearchPresentation(
        id,
        title,
        poster_path
    )

fun List<MovieLocalSearchData>.mapToPresentation(): List<MovieLocalSearchPresentation> =
    map { it.mapToPresentation() }