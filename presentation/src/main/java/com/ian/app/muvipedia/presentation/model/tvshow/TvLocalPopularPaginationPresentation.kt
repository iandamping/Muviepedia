package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalPopularPaginationData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvLocalPopularPaginationPresentation(var id: Int?, var name: String?, var poster_path: String?)
fun TvLocalPopularPaginationData.mapToPresentation(): TvLocalPopularPaginationPresentation =
    TvLocalPopularPaginationPresentation(
        id,
        name,
        poster_path
    )
fun List<TvLocalPopularPaginationData>.mapToPresentation(): List<TvLocalPopularPaginationPresentation> = map { it.mapToPresentation() }