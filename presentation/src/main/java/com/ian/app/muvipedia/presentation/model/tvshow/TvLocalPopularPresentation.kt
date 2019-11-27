package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalPopularData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvLocalPopularPresentation(var id: Int?, var name: String?, var poster_path: String?)

fun TvLocalPopularData.mapToPresentation(): TvLocalPopularPresentation =
    TvLocalPopularPresentation(
        id,
        name,
        poster_path
    )
fun List<TvLocalPopularData>.mapToPresentation(): List<TvLocalPopularPresentation> = map { it.mapToPresentation() }