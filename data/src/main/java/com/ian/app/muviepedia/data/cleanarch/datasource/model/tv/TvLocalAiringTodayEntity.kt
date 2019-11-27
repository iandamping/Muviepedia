package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalAiringTodayData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_airing_data")
data class TvLocalAiringTodayEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_tv_airing_id") var id: Int?,
    @ColumnInfo(name = "imdb_tv_airing_tittle") var name: String?,
    @ColumnInfo(name = "imdb_tv_airing_poster_path") var poster_path: String?
)

fun TvLocalAiringTodayEntity.mapToDomain(): TvLocalAiringTodayData =
        TvLocalAiringTodayData(id, name, poster_path)

fun List<TvLocalAiringTodayEntity>.mapToDomain(): List<TvLocalAiringTodayData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalAiringTodayEntity>>.mapToDomain(): Flow<List<TvLocalAiringTodayData>> = map { it.mapToDomain() }