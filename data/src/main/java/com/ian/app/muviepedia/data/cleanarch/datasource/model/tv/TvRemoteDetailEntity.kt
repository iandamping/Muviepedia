package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig
import com.ian.app.muviepedia.model.tvshow.TvRemoteDetailData

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

data class TvRemoteDetailEntity(
    @field:SerializedName("backdrop_path") var backdrop_path: String?,
    @field:SerializedName("episode_run_time") var episode_run_time: List<Int>?,
    @field:SerializedName("first_air_date") var first_air_date: String?,
    @field:SerializedName("homepage") var homepage: String?,
    @field:SerializedName("id") var id: Int?,
    @field:SerializedName("in_production") var in_production: Boolean?,
    @field:SerializedName("languages") var languages: List<String>?,
    @field:SerializedName("last_air_date") var last_air_date: String?,
    @field:SerializedName("name") var name: String?,
    @field:SerializedName("next_episode_to_air") var next_episode_to_air: Any?,
    @field:SerializedName("number_of_episodes") var number_of_episodes: Int?,
    @field:SerializedName("number_of_seasons") var number_of_seasons: Int?,
    @field:SerializedName("origin_country") var origin_country: List<String>?,
    @field:SerializedName("original_language") var original_language: String?,
    @field:SerializedName("original_name") var original_name: String?,
    @field:SerializedName("overview") var overview: String?,
    @field:SerializedName("popularity") var popularity: Double?,
    @field:SerializedName("poster_path") var poster_path: String?,
    @field:SerializedName("status") var status: String?,
    @field:SerializedName("type") var type: String?,
    @field:SerializedName("vote_average") var vote_average: Double?,
    @field:SerializedName("vote_count") var vote_count: Int?
)

fun TvRemoteDetailEntity.mapToDomain(): TvRemoteDetailData = TvRemoteDetailData(
    BuildConfig.imageFormatter + backdrop_path,
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
   BuildConfig.imageFormatter + poster_path,
    status,
    type,
    vote_average,
    vote_count
)
