package com.ian.junemon.spe_learning_mvvm.tv.data.remote

import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvSearchLocalData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
data class TvRemoteData(
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

