package com.ian.app.muviepedia.data.data_source.movie.data.remote

import com.ian.app.muviepedia.data.BuildConfig
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MovieSaveDetailData


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

data class DetailMovieData(
        var adult: Boolean,
        var backdrop_path: String,
        var belongs_to_collection: BelongsToCollection,
        var budget: Int,
        var genres: List<Genre>,
        var id: Int,
        var imdb_id: String,
        var original_language: String,
        var original_title: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var production_companies: List<ProductionCompany>,
        var production_countries: List<ProductionCountry>,
        var release_date: String,
        var revenue: String,
        var runtime: Int,
        var spoken_languages: List<SpokenLanguage>,
        var status: String,
        var tagline: String,
        var title: String,
        var video: Boolean,
        var vote_average: Double,
        var vote_count: Int
) {

    data class BelongsToCollection(var id: Int, var name: String, var posterPath: String, var backdropPath: String)
    data class Genre(var id: Int, var name: String)
    data class ProductionCompany(var id: Int, var logoPath: String, var name: String, var originCountry: String)
    data class ProductionCountry(var iso31661: String, var name: String)
    data class SpokenLanguage(var iso31661: String, var name: String)

}

fun DetailMovieData.toSaveDetail(): MovieSaveDetailData {
    return MovieSaveDetailData(null, id, budget, revenue, release_date, runtime, vote_average, title, tagline, overview, BuildConfig.imageFormatter + poster_path)
}
