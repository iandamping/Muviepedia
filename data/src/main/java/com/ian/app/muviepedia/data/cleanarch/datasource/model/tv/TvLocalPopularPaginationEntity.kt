package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularPaginationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_popular_pagination_data")
data class TvLocalPopularPaginationEntity(
    @PrimaryKey
@ColumnInfo(name = "imdb_tv_popular_pagination_id") var id: Int?,
    @ColumnInfo(name = "imdb_tv_popular_pagination_tittle") var name: String?,
    @ColumnInfo(name = "imdb_tv_popular_pagination_poster_path") var poster_path: String?
)

fun TvLocalPopularPaginationEntity.mapToDomain(): TvLocalPopularPaginationData =
    TvLocalPopularPaginationData(id, name, poster_path)

fun List<TvLocalPopularPaginationEntity>.mapToDomain(): List<TvLocalPopularPaginationData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalPopularPaginationEntity>>.mapToDomain(): Flow<List<TvLocalPopularPaginationData>> = map { it.mapToDomain() }