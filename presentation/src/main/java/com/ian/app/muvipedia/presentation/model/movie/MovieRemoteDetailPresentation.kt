package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieRemoteDetailData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieRemoteDetailPresentation(
    var adult: Boolean,
    var backdrop_path: String,
    var budget: Int,
    var id: Int,
    var imdb_id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var revenue: String,
    var runtime: Int,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)

fun MovieRemoteDetailPresentation.mapToLocalData(): MovieLocalSaveDetailPresentation =
    MovieLocalSaveDetailPresentation(
        null,
        id,
        budget,
        revenue,
        release_date,
        runtime,
        vote_average,
        title,
        tagline,
        overview,
        poster_path
    )

fun MovieRemoteDetailData.mapToPresentation(): MovieRemoteDetailPresentation =
    MovieRemoteDetailPresentation(
        adult,
        backdrop_path,
        budget,
        id,
        imdb_id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
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