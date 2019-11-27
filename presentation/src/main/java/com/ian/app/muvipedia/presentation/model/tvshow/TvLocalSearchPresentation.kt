package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalSearchData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvLocalSearchPresentation(var id: Int?, var name: String?, var poster_path: String?)

fun TvLocalSearchData.mapToPresentation(): TvLocalSearchPresentation =
    TvLocalSearchPresentation(
        id,
        name,
        poster_path
    )
fun List<TvLocalSearchData>.mapToPresentation(): List<TvLocalSearchPresentation> = map { it.mapToPresentation() }