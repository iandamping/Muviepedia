package com.ian.app.muviepedia.model.tvshow

/**
 * Created by Ian Damping on 26?,November?,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvRemoteDetailData(
    var backdrop_path: String?,
    var episode_run_time: List<Int>?,
    var first_air_date: String?,
    var homepage: String?,
    var id: Int?,
    var in_production: Boolean?,
    var languages: List<String>?,
    var last_air_date: String?,
    var name: String?,
    var next_episode_to_air: Any?,
    var number_of_episodes: Int?,
    var number_of_seasons: Int?,
    var origin_country: List<String>?,
    var original_language: String?,
    var original_name: String?,
    var overview: String?,
    var popularity: Double?,
    var poster_path: String?,
    var status: String?,
    var type: String?,
    var vote_average: Double?,
    var vote_count: Int?
)