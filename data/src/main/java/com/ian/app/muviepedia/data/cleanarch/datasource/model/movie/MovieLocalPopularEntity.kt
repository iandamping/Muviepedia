package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.movie.MovieLocalPopularData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_popular_data")
data class MovieLocalPopularEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_movie_popular_id") var id: Int?,
    @ColumnInfo(name = "imdb_movie_popular_tittle") var title: String?,
    @ColumnInfo(name = "imdb_movie_popular_poster_path") var poster_path: String?
)

fun MovieLocalPopularEntity.mapToDomain(): MovieLocalPopularData =
        MovieLocalPopularData(
                id,
                title,
                poster_path
        )

fun List<MovieLocalPopularEntity>.mapToDomain(): List<MovieLocalPopularData> = map { it?.mapToDomain() }

fun Flow<List<MovieLocalPopularEntity>>.mapToDomain(): Flow<List<MovieLocalPopularData>> = map { it.mapToDomain() }