package com.ian.app.muvipedia.presentation.model.tvshow

import com.ian.app.muviepedia.model.tvshow.TvRemoteData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class TvRemotePresentation(
    var original_name: String?,
    var genre_ids: List<Int>?,
    var name: String?,
    var popularity: Double?,
    var origin_country: List<String>?,
    var vote_count: Int?,
    var first_air_date: String?,
    var backdrop_path: String?,
    var original_language: String?,
    var id: Int?,
    var vote_average: Double?,
    var overview: String?,
    var poster_path: String?
)

fun TvRemoteData.mapToPresentation(): TvRemotePresentation =
    TvRemotePresentation(
        original_name,
        genre_ids,
        name,
        popularity,
        origin_country,
        vote_count,
        first_air_date,
        backdrop_path,
        original_language,
        id,
        vote_average,
        overview,
        poster_path
    )

fun TvRemotePresentation.mapToDomain(): TvRemoteData =
    TvRemoteData(
        original_name,
        genre_ids,
        name,
        popularity,
        origin_country,
        vote_count,
        first_air_date,
        backdrop_path,
        original_language,
        id,
        vote_average,
        overview,
        poster_path
    )

fun List<TvRemoteData>.mapToPresentation(): List<TvRemotePresentation> =
    map { it.mapToPresentation() }

fun List<TvRemotePresentation>.mapToData(): List<TvRemoteData> = map { it.mapToDomain() }