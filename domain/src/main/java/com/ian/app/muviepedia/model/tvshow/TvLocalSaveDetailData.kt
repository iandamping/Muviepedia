package com.ian.app.muviepedia.model.tvshow

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvLocalSaveDetailData(
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