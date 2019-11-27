package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedPaginationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_toprated_pagination_data")
data class TvLocalTopRatedPaginationEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_tv_toprated_pagination_id") var id: Int?,
    @ColumnInfo(name = "imdb_tv_toprated_pagination_tittle") var name: String?,
    @ColumnInfo(name = "imdb_tv_toprated_pagination_poster_path") var poster_path: String?
)

fun TvLocalTopRatedPaginationEntity.mapToDomain(): TvLocalTopRatedPaginationData =
        TvLocalTopRatedPaginationData(id, name, poster_path)

fun List<TvLocalTopRatedPaginationEntity>.mapToDomain(): List<TvLocalTopRatedPaginationData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalTopRatedPaginationEntity>>.mapToDomain(): Flow<List<TvLocalTopRatedPaginationData>> = map { it.mapToDomain() }