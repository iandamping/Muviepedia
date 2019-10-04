package com.ian.app.muviepedia.data.data_source.tv.data.remote

import com.google.gson.annotations.SerializedName
import com.ian.app.muviepedia.data.BuildConfig.imageFormatter
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.*
import com.ian.app.muviepedia.tv.data.local.model.TvAiringTodayData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
data class TvRemoteData(
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


suspend fun List<TvRemoteData>.toAiringToday(scope: CoroutineScope): MutableList<TvAiringTodayData> {
    return withContext(scope.coroutineContext) {
        this@toAiringToday.map {
            TvAiringTodayData(it.id, it.name, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<TvRemoteData>.toPopularTv(scope: CoroutineScope): MutableList<TvPopularData> {
    return withContext(scope.coroutineContext) {
        this@toPopularTv.map {
            TvPopularData(it.id, it.name, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<TvRemoteData>.toTopRatedTv(scope: CoroutineScope): MutableList<TvTopRatedData> {
    return withContext(scope.coroutineContext) {
        this@toTopRatedTv.map {
            TvTopRatedData(it.id, it.name, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<TvRemoteData>.toPaginationPopularTv(scope: CoroutineScope): MutableList<TvPopularPaginationData> {
    return withContext(scope.coroutineContext) {
        this@toPaginationPopularTv.map {
            TvPopularPaginationData(it.id, it.name, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<TvRemoteData>.toPaginationTopRatedTv(scope: CoroutineScope): MutableList<TvTopRatedPaginationData> {
    return withContext(scope.coroutineContext) {
        this@toPaginationTopRatedTv.map {
            TvTopRatedPaginationData(it.id, it.name, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

suspend fun List<TvRemoteData>.toSearchTv(scope: CoroutineScope): MutableList<TvSearchLocalData> {
    return withContext(scope.coroutineContext) {
        this@toSearchTv.map {
            TvSearchLocalData(it.id, it.name, imageFormatter + it.poster_path)
        }.toMutableList()
    }
}

