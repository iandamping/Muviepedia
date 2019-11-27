package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvLocalSaveDetailData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvLocalSaveDetailPresentation(
    var localID: Int?,
    var id: Int?,
    var number_of_episodes: Int?,
    var first_air_date: String?,
    var vote_average: Double?,
    var name: String?,
    var original_name: String?,
    var overview: String?,
    var poster_path: String?
)
fun TvLocalSaveDetailData.mapToPresentation(): TvLocalSaveDetailPresentation = TvLocalSaveDetailPresentation(localID, id, number_of_episodes, first_air_date, vote_average, name, original_name, overview, poster_path)

fun TvLocalSaveDetailPresentation.mapToData(): TvLocalSaveDetailData =
    TvLocalSaveDetailData(
        localID,
        id,
        number_of_episodes,
        first_air_date,
        vote_average,
        name,
        original_name,
        overview,
        poster_path
    )

fun List<TvLocalSaveDetailData>.mapToPresentation(): List<TvLocalSaveDetailPresentation> = map { it.mapToPresentation() }
