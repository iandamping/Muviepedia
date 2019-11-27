package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_popular_data")
data class TvLocalPopularEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_tv_popular_id") var id: Int?,
    @ColumnInfo(name = "imdb_tv_popular_tittle") var name: String?,
    @ColumnInfo(name = "imdb_tv_popular_poster_path") var poster_path: String?
)

fun TvLocalPopularEntity.mapToDomain(): TvLocalPopularData =
        TvLocalPopularData(id, name, poster_path)

fun List<TvLocalPopularEntity>.mapToDomain(): List<TvLocalPopularData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalPopularEntity>>.mapToDomain(): Flow<List<TvLocalPopularData>> = map { it.mapToDomain() }