package com.ian.app.muviepedia.model.movie

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieRemoteData(
    var vote_count: Int?,
    var id: Int?,
    var video: Boolean?,
    var vote_average: Double?,
    var title: String?,
    var popularity: Double?,
    var poster_path: String?,
    var original_language: String?,
    var original_title: String?,
    var genre_ids: List<Int>?,
    var backdrop_path: String?,
    var adult: Boolean?,
    var overview: String?,
    var release_date: String?
)