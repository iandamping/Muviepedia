package com.ian.junemon.spe_learning_mvvm.model

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class MovieData(
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
