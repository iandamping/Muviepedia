package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalAiringTodayData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvLocalAiringPresentation(var id: Int?, var name: String?, var poster_path: String?)

fun TvLocalAiringTodayData.mapToPresentation(): TvLocalAiringPresentation =
    TvLocalAiringPresentation(
        id,
        name,
        poster_path
    )

fun List<TvLocalAiringTodayData>.mapToPresentation(): List<TvLocalAiringPresentation> =
    map { it.mapToPresentation() }