package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedPaginationData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvLocalTopRatedPaginationPresentation(var id: Int?, var name: String?, var poster_path: String?)

fun TvLocalTopRatedPaginationData.mapToPresentation(): TvLocalTopRatedPaginationPresentation =
    TvLocalTopRatedPaginationPresentation(
        id,
        name,
        poster_path
    )
fun List<TvLocalTopRatedPaginationData>.mapToPresentation(): List<TvLocalTopRatedPaginationPresentation> = map { it.mapToPresentation() }