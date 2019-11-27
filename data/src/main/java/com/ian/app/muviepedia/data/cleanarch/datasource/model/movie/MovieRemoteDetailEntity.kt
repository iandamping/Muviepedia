package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig.imageFormatter
import com.ian.app.muviepedia.model.movie.MovieRemoteDetailData

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

data class MovieRemoteDetailEntity(
    @field:SerializedName("adult") var adult: Boolean,
    @field:SerializedName("backdrop_path") var backdrop_path: String,
    @field:SerializedName("budget") var budget: Int,
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("imdb_id") var imdb_id: String,
    @field:SerializedName("original_language") var original_language: String,
    @field:SerializedName("original_title") var original_title: String,
    @field:SerializedName("overview") var overview: String,
    @field:SerializedName("popularity") var popularity: Double,
    @field:SerializedName("poster_path") var poster_path: String,
    @field:SerializedName("release_date") var release_date: String,
    @field:SerializedName("revenue") var revenue: String,
    @field:SerializedName("runtime") var runtime: Int,
    @field:SerializedName("status") var status: String,
    @field:SerializedName("tagline") var tagline: String,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("video") var video: Boolean,
    @field:SerializedName("vote_average") var vote_average: Double,
    @field:SerializedName("vote_count") var vote_count: Int
)

fun MovieRemoteDetailEntity.mapToDomain(): MovieRemoteDetailData =
    MovieRemoteDetailData(
        adult,
        imageFormatter + backdrop_path,
        budget,
        id,
        imdb_id,
        original_language,
        original_title,
        overview,
        popularity,
      imageFormatter + poster_path,
        release_date,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        vote_average,
        vote_count
    )
