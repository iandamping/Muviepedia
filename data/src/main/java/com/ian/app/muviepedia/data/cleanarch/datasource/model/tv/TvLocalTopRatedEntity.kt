package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_toprated_data")
data class TvLocalTopRatedEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_tv_toprated_id") var id: Int?,
    @ColumnInfo(name = "imdb_tv_toprated_tittle") var name: String?,
    @ColumnInfo(name = "imdb_tv_toprated_poster_path") var poster_path: String?
)
fun TvLocalTopRatedEntity.mapToDomain(): TvLocalTopRatedData =
        TvLocalTopRatedData(id, name, poster_path)

fun List<TvLocalTopRatedEntity>.mapToDomain(): List<TvLocalTopRatedData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalTopRatedEntity>>.mapToDomain(): Flow<List<TvLocalTopRatedData>> = map { it.mapToDomain() }