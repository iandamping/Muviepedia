package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvLocalTopRatedPresentation(var id: Int?, var name: String?, var poster_path: String?)

fun TvLocalTopRatedData.mapToPresentation(): TvLocalTopRatedPresentation =
    TvLocalTopRatedPresentation(
        id,
        name,
        poster_path
    )
fun List<TvLocalTopRatedData>.mapToPresentation(): List<TvLocalTopRatedPresentation> = map { it.mapToPresentation() }