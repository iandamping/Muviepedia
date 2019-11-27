package com.ian.app.muviepedia.model.tvshow

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvRemoteData(
    var original_name: String?,
    var genre_ids: List<Int>?,
    var name: String?,
    var popularity: Double?,
    var origin_country: List<String>?,
    var vote_count: Int?,
    var first_air_date: String?,
    var backdrop_path: String?,
    var original_language: String?,
    var id: Int?,
    var vote_average: Double?,
    var overview: String?,
    var poster_path: String?
)