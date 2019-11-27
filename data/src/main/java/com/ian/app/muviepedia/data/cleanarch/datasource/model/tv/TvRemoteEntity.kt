package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig.imageFormatter
import com.ian.app.muviepedia.model.tvshow.TvRemoteData

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
data class TvRemoteEntity(
    @field:SerializedName("original_name") var original_name: String?,
    @field:SerializedName("genre_ids") var genre_ids: List<Int>?,
    @field:SerializedName("name") var name: String?,
    @field:SerializedName("popularity") var popularity: Double?,
    @field:SerializedName("origin_country") var origin_country: List<String>?,
    @field:SerializedName("vote_count") var vote_count: Int?,
    @field:SerializedName("first_air_date") var first_air_date: String?,
    @field:SerializedName("backdrop_path") var backdrop_path: String?,
    @field:SerializedName("original_language") var original_language: String?,
    @field:SerializedName("id") var id: Int?,
    @field:SerializedName("vote_average") var vote_average: Double?,
    @field:SerializedName("overview") var overview: String?,
    @field:SerializedName("poster_path") var poster_path: String?
)
fun TvRemoteEntity.mapToDomain(): TvRemoteData = TvRemoteData(original_name, genre_ids, name, popularity, origin_country, vote_count, first_air_date, imageFormatter + backdrop_path, original_language, id, vote_average, overview, imageFormatter + poster_path)

fun TvRemoteEntity.mapToAiringTodayTv(): TvLocalAiringTodayEntity = TvLocalAiringTodayEntity(id, name, imageFormatter + poster_path)

fun TvRemoteEntity.mapToPopularTv(): TvLocalPopularEntity = TvLocalPopularEntity(id, name, imageFormatter + poster_path)

fun TvRemoteEntity.mapToTopRatedTv(): TvLocalTopRatedEntity = TvLocalTopRatedEntity(id, name, imageFormatter + poster_path)

fun TvRemoteEntity.mapToSearchTv(): TvLocalSearchEntity = TvLocalSearchEntity(id, name, imageFormatter + poster_path)

fun TvRemoteEntity.mapToPaginationPopularTv(): TvLocalPopularPaginationEntity = TvLocalPopularPaginationEntity(id, name, imageFormatter + poster_path)

fun TvRemoteEntity.mapToPaginationTopRatedTv(): TvLocalTopRatedPaginationEntity = TvLocalTopRatedPaginationEntity(id, name, imageFormatter + poster_path)

fun List<TvRemoteEntity>.mapToDomain(): List<TvRemoteData> = map { it.mapToDomain() }

fun List<TvRemoteEntity>.mapToAiringTodayTv(): List<TvLocalAiringTodayEntity> = map { it?.mapToAiringTodayTv() }

fun List<TvRemoteEntity>.mapToPopularTv(): List<TvLocalPopularEntity> = map { it?.mapToPopularTv() }

fun List<TvRemoteEntity>.mapToTopRatedTv(): List<TvLocalTopRatedEntity> = map { it?.mapToTopRatedTv() }

fun List<TvRemoteEntity>.mapToSearchTv(): List<TvLocalSearchEntity> = map { it?.mapToSearchTv() }

fun List<TvRemoteEntity>.mapToPaginationPopularTv(): List<TvLocalPopularPaginationEntity> = map { it?.mapToPaginationPopularTv() }

fun List<TvRemoteEntity>.mapToPaginationTopRatedTv(): List<TvLocalTopRatedPaginationEntity> = map { it?.mapToPaginationTopRatedTv() }
