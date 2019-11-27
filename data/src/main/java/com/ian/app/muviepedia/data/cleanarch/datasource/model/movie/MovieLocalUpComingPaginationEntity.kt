package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingPaginationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_upcoming_pagination_data")
data class MovieLocalUpComingPaginationEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_movie_upcoming_pagination__id") var id: Int?,
    @ColumnInfo(name = "imdb_movie_upcoming_pagination__tittle") var title: String?,
    @ColumnInfo(name = "imdb_movie_upcoming_pagination__poster_path") var poster_path: String?
)

fun MovieLocalUpComingPaginationEntity.mapToDomain(): MovieLocalUpComingPaginationData =
        MovieLocalUpComingPaginationData(
                id,
                title,
                poster_path
        )

fun List<MovieLocalUpComingPaginationEntity>.mapToDomain(): List<MovieLocalUpComingPaginationData> = map { it?.mapToDomain() }

fun Flow<List<MovieLocalUpComingPaginationEntity>>.mapToDomain(): Flow<List<MovieLocalUpComingPaginationData>> = map { it.mapToDomain() }