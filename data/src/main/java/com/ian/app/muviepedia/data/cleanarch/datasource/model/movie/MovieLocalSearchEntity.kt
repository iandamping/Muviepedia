package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.movie.MovieLocalSearchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_search_data")
data class MovieLocalSearchEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_movie_search_id") var id: Int?,
    @ColumnInfo(name = "imdb_movie_search_tittle") var title: String?,
    @ColumnInfo(name = "imdb_movie_search_poster_path") var poster_path: String?
)

fun MovieLocalSearchEntity.mapToDomain(): MovieLocalSearchData =
        MovieLocalSearchData(
                id,
                title,
                poster_path
        )

fun List<MovieLocalSearchEntity>.mapToDomain(): List<MovieLocalSearchData> = map { it?.mapToDomain() }

fun Flow<List<MovieLocalSearchEntity>>.mapToDomain(): Flow<List<MovieLocalSearchData>> = map { it.mapToDomain() }