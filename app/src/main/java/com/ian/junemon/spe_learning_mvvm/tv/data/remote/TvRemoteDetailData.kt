package com.ian.junemon.spe_learning_mvvm.tv.data.remote

import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvSaveDetailData


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

data class DetailTvData(
        var backdrop_path: String,
        var created_by: List<CreatedBy>,
        var episode_run_time: List<Int>,
        var first_air_date: String,
        var genres: List<Genre>,
        var homepage: String,
        var id: Int,
        var in_production: Boolean,
        var languages: List<String>,
        var last_air_date: String,
        var last_episode_to_air: LastEpisodeToAir,
        var name: String,
        var networks: List<Network>,
        var next_episode_to_air: Any,
        var number_of_episodes: Int,
        var number_of_seasons: Int,
        var origin_country: List<String>,
        var original_language: String,
        var original_name: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var production_companies: List<ProductionCompany>,
        var seasons: List<Season>,
        var status: String,
        var type: String,
        var vote_average: Double,
        var vote_count: Int
) {
    data class CreatedBy(
            var credit_id: String,
            var gender: Int,
            var id: Int,
            var name: String,
            var profile_path: String
    )

    data class Genre(var id: Int, var name: String)

    data class LastEpisodeToAir(
            var air_date: String,
            var episode_number: Int,
            var id: Int,
            var name: String,
            var overview: String,
            var production_code: String,
            var season_number: Int,
            var show_id: Int,
            var still_path: String,
            var vote_average: Double,
            var vote_count: Int
    )

    data class Network(var id: Int, var logo_path: String, var name: String, var origin_country: String)

    data class ProductionCompany(var id: Int, var logo_path: String, var name: String, var origin_country: String)

    data class Season(var air_date: String, var episode_count: Int, var id: Int, var name: String, var overview: String, var poster_path: String, var season_number: Int)
}

fun DetailTvData.toDatabase():TvSaveDetailData{
    return TvSaveDetailData(null,id,number_of_episodes,first_air_date,vote_average,name,original_name,overview, poster_path)
}
