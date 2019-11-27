package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_upcoming_data")
data class MovieLocalUpComingEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_movie_upcoming_id") var id: Int?,
    @ColumnInfo(name = "imdb_movie_upcoming_tittle") var title: String?,
    @ColumnInfo(name = "imdb_movie_upcoming_poster_path") var poster_path: String?
)

fun MovieLocalUpComingEntity.mapToDomain(): MovieLocalUpComingData =
        MovieLocalUpComingData(
                id,
                title,
                poster_path
        )

fun List<MovieLocalUpComingEntity>.mapToDomain(): List<MovieLocalUpComingData> = map { it?.mapToDomain() }

fun Flow<List<MovieLocalUpComingEntity>>.mapToDomain(): Flow<List<MovieLocalUpComingData>> = map { it.mapToDomain() }