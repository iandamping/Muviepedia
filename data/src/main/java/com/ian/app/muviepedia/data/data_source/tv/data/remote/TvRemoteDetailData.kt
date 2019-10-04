package com.ian.app.muviepedia.data.data_source.tv.data.remote

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.TvSaveDetailData


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

data class DetailTvData(
        @field:SerializedName("backdrop_path") var backdrop_path: String,
        @field:SerializedName("created_by") var created_by: List<CreatedBy>,
        @field:SerializedName("episode_run_time") var episode_run_time: List<Int>,
        @field:SerializedName("first_air_date") var first_air_date: String,
        @field:SerializedName("genres") var genres: List<Genre>,
        @field:SerializedName("homepage") var homepage: String,
        @field:SerializedName("id") var id: Int,
        @field:SerializedName("in_production") var in_production: Boolean,
        @field:SerializedName("languages") var languages: List<String>,
        @field:SerializedName("last_air_date") var last_air_date: String,
        @field:SerializedName("last_episode_to_air") var last_episode_to_air: LastEpisodeToAir,
        @field:SerializedName("name") var name: String,
        @field:SerializedName("networks") var networks: List<Network>,
        @field:SerializedName("next_episode_to_air") var next_episode_to_air: Any,
        @field:SerializedName("number_of_episodes") var number_of_episodes: Int,
        @field:SerializedName("number_of_seasons") var number_of_seasons: Int,
        @field:SerializedName("origin_country") var origin_country: List<String>,
        @field:SerializedName("original_language") var original_language: String,
        @field:SerializedName("original_name") var original_name: String,
        @field:SerializedName("overview") var overview: String,
        @field:SerializedName("popularity") var popularity: Double,
        @field:SerializedName("poster_path") var poster_path: String,
        @field:SerializedName("production_companies") var production_companies: List<ProductionCompany>,
        @field:SerializedName("seasons") var seasons: List<Season>,
        @field:SerializedName("status") var status: String,
        @field:SerializedName("type") var type: String,
        @field:SerializedName("vote_average") var vote_average: Double,
        @field:SerializedName("vote_count") var vote_count: Int
) {
    data class CreatedBy(
             @field:SerializedName("credit_id") var credit_id: String,
             @field:SerializedName("gender") var gender: Int,
             @field:SerializedName("id") var id: Int,
             @field:SerializedName("name") var name: String,
             @field:SerializedName("profile_path") var profile_path: String
    )

    data class Genre( @field:SerializedName("id") var id: Int,
                      @field:SerializedName("name") var name: String)

    data class LastEpisodeToAir(
             @field:SerializedName("air_date") var air_date: String,
             @field:SerializedName("episode_number") var episode_number: Int,
             @field:SerializedName("id") var id: Int,
             @field:SerializedName("name") var name: String,
             @field:SerializedName("overview") var overview: String,
             @field:SerializedName("production_code") var production_code: String,
             @field:SerializedName("season_number") var season_number: Int,
             @field:SerializedName("show_id") var show_id: Int,
             @field:SerializedName("still_path") var still_path: String,
             @field:SerializedName("vote_average") var vote_average: Double,
             @field:SerializedName("vote_count") var vote_count: Int
    )

    data class Network( @field:SerializedName("id") var id: Int,
                        @field:SerializedName("logo_path") var logo_path: String,
                        @field:SerializedName("name") var name: String,
                        @field:SerializedName("origin_country") var origin_country: String)

    data class ProductionCompany( @field:SerializedName("id") var id: Int,
                                  @field:SerializedName("logo_path") var logo_path: String,
                                  @field:SerializedName("name") var name: String,
                                  @field:SerializedName("origin_country") var origin_country: String)

    data class Season( @field:SerializedName("air_date") var air_date: String,
                       @field:SerializedName("episode_count") var episode_count: Int,
                       @field:SerializedName("id") var id: Int,
                       @field:SerializedName("name")var name: String,
                       @field:SerializedName("overview") var overview: String,
                       @field:SerializedName("poster_path") var poster_path: String,
                       @field:SerializedName("season_number") var season_number: Int)
}

fun DetailTvData.toDatabase(): TvSaveDetailData {
    return TvSaveDetailData(null, id, number_of_episodes, first_air_date, vote_average, name, original_name, overview, poster_path)
}
