package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieRemoteData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieRemotePresentation(
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

fun MovieRemoteData.mapToPresentation(): MovieRemotePresentation =
    MovieRemotePresentation(
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

fun MovieRemotePresentation.mapToDomain(): MovieRemoteData =
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

fun List<MovieRemoteData>.mapToPresentation(): List<MovieRemotePresentation> = map { it.mapToPresentation() }

fun List<MovieRemotePresentation>.mapToData(): List<MovieRemoteData> = map { it.mapToDomain() }
