package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvRemoteDetailData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvRemoteDetailPresentation(
    var backdrop_path: String,
    var episode_run_time: List<Int>,
    var first_air_date: String,
    var homepage: String,
    var id: Int,
    var in_production: Boolean,
    var languages: List<String>,
    var last_air_date: String,
    var name: String,
    var next_episode_to_air: Any,
    var number_of_episodes: Int,
    var number_of_seasons: Int,
    var origin_country: List<String>,
    var original_language: String,
    var original_name: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var status: String,
    var type: String,
    var vote_average: Double,
    var vote_count: Int
)

fun TvRemoteDetailPresentation.mapToLocalData(): TvLocalSaveDetailPresentation =
    TvLocalSaveDetailPresentation(
        null,
        id,
        number_of_episodes,
        first_air_date,
        vote_average,
        name,
        original_name,
        overview,
        poster_path
    )

fun TvRemoteDetailData.mapToPresentation(): TvRemoteDetailPresentation =
    TvRemoteDetailPresentation(
        backdrop_path,
        episode_run_time,
        first_air_date,
        homepage,
        id,
        in_production,
        languages,
        last_air_date,
        name,
        next_episode_to_air,
        number_of_episodes,
        number_of_seasons,
        origin_country,
        original_language,
        original_name,
        overview,
        popularity,
        poster_path,
        status,
        type,
        vote_average,
        vote_count
    )