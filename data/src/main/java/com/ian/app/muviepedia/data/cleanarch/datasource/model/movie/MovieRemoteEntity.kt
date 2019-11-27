package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig.imageFormatter
import com.ian.app.muviepedia.model.movie.MovieRemoteData

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
data class MovieRemoteEntity(
    @field:SerializedName("vote_count")var vote_count: Int?,
    @field:SerializedName("id") var id: Int?,
    @field:SerializedName("video")var video: Boolean?,
    @field:SerializedName("vote_average")var vote_average: Double?,
    @field:SerializedName("title")var title: String?,
    @field:SerializedName("popularity")var popularity: Double?,
    @field:SerializedName("poster_path")var poster_path: String?,
    @field:SerializedName("original_language")var original_language: String?,
    @field:SerializedName("original_title")var original_title: String?,
    @field:SerializedName("genre_ids")var genre_ids: List<Int>?,
    @field:SerializedName("backdrop_path")var backdrop_path: String?,
    @field:SerializedName("adult")var adult: Boolean?,
    @field:SerializedName("overview")var overview: String?,
    @field:SerializedName("release_date")var release_date: String?
)

fun MovieRemoteEntity.mapToDomain(): MovieRemoteData =
    MovieRemoteData(
        vote_count,
        id,
        video,
        vote_average,
        title,
        popularity,
        poster_path,
        original_language,
        original_title,
        genre_ids,
        backdrop_path,
        adult,
        overview,
        release_date
    )

fun MovieRemoteEntity.mapToUpComingMovie(): MovieLocalUpComingEntity = MovieLocalUpComingEntity(id, title, imageFormatter + poster_path)

fun MovieRemoteEntity.mapToNowPlayingMovie(): MovieLocalNowPlayingEntity = MovieLocalNowPlayingEntity(id, title, imageFormatter + poster_path)

fun MovieRemoteEntity.mapToPopularMovie(): MovieLocalPopularEntity = MovieLocalPopularEntity(id, title, imageFormatter + poster_path)

fun MovieRemoteEntity.mapToSearchMovie(): MovieLocalSearchEntity = MovieLocalSearchEntity(id, title, imageFormatter + poster_path)

fun MovieRemoteEntity.mapToPaginationPopularMovie(): MovieLocalPopularPaginationEntity = MovieLocalPopularPaginationEntity(id, title, imageFormatter + poster_path)

fun MovieRemoteEntity.mapToPaginationUpComingMovie(): MovieLocalUpComingPaginationEntity = MovieLocalUpComingPaginationEntity(id, title, imageFormatter + poster_path)

fun List<MovieRemoteEntity>.mapToDomain(): List<MovieRemoteData> = map { it.mapToDomain() }

fun List<MovieRemoteEntity>.mapToUpComingMovie(): List<MovieLocalUpComingEntity> = map { it?.mapToUpComingMovie() }

fun List<MovieRemoteEntity>.mapToNowPlayingMovie(): List<MovieLocalNowPlayingEntity> = map { it?.mapToNowPlayingMovie() }

fun List<MovieRemoteEntity>.mapToPopularMovie(): List<MovieLocalPopularEntity> = map { it?.mapToPopularMovie() }

fun List<MovieRemoteEntity>.mapToSearchMovie(): List<MovieLocalSearchEntity> = map { it?.mapToSearchMovie() }

fun List<MovieRemoteEntity>.mapToPaginationPopularMovie(): List<MovieLocalPopularPaginationEntity> = map { it?.mapToPaginationPopularMovie() }

fun List<MovieRemoteEntity>.mapToPaginationUpComingMovie(): List<MovieLocalUpComingPaginationEntity> = map { it?.mapToPaginationUpComingMovie() }
