package com.ian.app.muviepedia.data.data_source.movie.data.remote

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MovieSaveDetailData


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

data class DetailMovieData(
        @field:SerializedName("adult") var adult: Boolean,
        @field:SerializedName("backdrop_path") var backdrop_path: String,
        @field:SerializedName("belongs_to_collection") var belongs_to_collection: BelongsToCollection,
        @field:SerializedName("budget") var budget: Int,
        @field:SerializedName("genres") var genres: List<Genre>,
        @field:SerializedName("id") var id: Int,
        @field:SerializedName("imdb_id") var imdb_id: String,
        @field:SerializedName("original_language") var original_language: String,
        @field:SerializedName("original_title") var original_title: String,
        @field:SerializedName("overview") var overview: String,
        @field:SerializedName("popularity") var popularity: Double,
        @field:SerializedName("poster_path") var poster_path: String,
        @field:SerializedName("production_companies") var production_companies: List<ProductionCompany>,
        @field:SerializedName("production_countries") var production_countries: List<ProductionCountry>,
        @field:SerializedName("release_date") var release_date: String,
        @field:SerializedName("revenue") var revenue: String,
        @field:SerializedName("runtime") var runtime: Int,
        @field:SerializedName("spoken_languages") var spoken_languages: List<SpokenLanguage>,
        @field:SerializedName("status") var status: String,
        @field:SerializedName("tagline") var tagline: String,
        @field:SerializedName("title") var title: String,
        @field:SerializedName("video") var video: Boolean,
        @field:SerializedName("vote_average") var vote_average: Double,
        @field:SerializedName("vote_count") var vote_count: Int
) {

    data class BelongsToCollection(@field:SerializedName("id") var id: Int,
                                   @field:SerializedName("name") var name: String,
                                   @field:SerializedName("posterPath") var posterPath: String,
                                   @field:SerializedName("backdropPath") var backdropPath: String)

    data class Genre(@field:SerializedName("id") var id: Int,
                     @field:SerializedName("name") var name: String)

    data class ProductionCompany(@field:SerializedName("id") var id: Int,
                                 @field:SerializedName("logoPath") var logoPath: String,
                                 @field:SerializedName("name") var name: String,
                                 @field:SerializedName("originCountry") var originCountry: String)

    data class ProductionCountry(@field:SerializedName("iso31661") var iso31661: String,
                                 @field:SerializedName("name") var name: String)

    data class SpokenLanguage(@field:SerializedName("iso31661") var iso31661: String,
                              @field:SerializedName("name") var name: String)

}

fun DetailMovieData.toSaveDetail(): MovieSaveDetailData {
    return MovieSaveDetailData(null, id, budget, revenue, release_date, runtime, vote_average, title, tagline, overview, BuildConfig.imageFormatter + poster_path)
}
