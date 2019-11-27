package com.ian.app.muvipedia.presentation.model.movie

import com.ian.app.muviepedia.model.movie.MovieLocalSaveDetailData

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class MovieLocalSaveDetailPresentation(
    var localID: Int?,
    var id: Int?,
    var budget: Int?,
    var revenue: String?,
    var release_date: String?,
    var runtime: Int?,
    var vote_average: Double?,
    var title: String?,
    var tagline: String?,
    var overview: String?,
    var poster_path: String?
)

fun MovieLocalSaveDetailData.mapToPresentation(): MovieLocalSaveDetailPresentation = MovieLocalSaveDetailPresentation(localID, id, budget, revenue, release_date, runtime, vote_average, title, tagline, overview, poster_path)

fun MovieLocalSaveDetailPresentation.mapToData(): MovieLocalSaveDetailData =
    MovieLocalSaveDetailData(
        localID,
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

fun List<MovieLocalSaveDetailData>.mapToPresentation(): List<MovieLocalSaveDetailPresentation> = map { it.mapToPresentation() }