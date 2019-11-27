package com.ian.app.muviepedia.model.movie

/**
 * Created by Ian Damping on 25,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalSaveDetailData(
    var localID: Int?,
    var id: Int?,
    var budget: Int?,
    var revenue: String?,
    var release_date: String?,
    var runtime: Int?,
    var vote_average: Double?,
    var title: String?,
    var tagline: String?,
    var overview: String?,
    var poster_path: String?
)
