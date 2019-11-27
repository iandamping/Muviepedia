package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalSearchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_search_data")
data class TvLocalSearchEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_tv_search_id") var id: Int?,
    @ColumnInfo(name = "imdb_tv_search_name") var name: String?,
    @ColumnInfo(name = "imdb_tv_search_poster_path") var poster_path: String?
)

fun TvLocalSearchEntity.mapToDomain(): TvLocalSearchData =
        TvLocalSearchData(id, name, poster_path)

fun List<TvLocalSearchEntity>.mapToDomain(): List<TvLocalSearchData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalSearchEntity>>.mapToDomain(): Flow<List<TvLocalSearchData>> = map { it.mapToDomain() }