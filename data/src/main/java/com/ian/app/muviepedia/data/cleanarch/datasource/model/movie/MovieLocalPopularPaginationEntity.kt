package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.movie.MovieLocalPopularPaginationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_popular_pagination_data")
data class MovieLocalPopularPaginationEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_movie_popular_pagination__id") var id: Int?,
    @ColumnInfo(name = "imdb_movie_popular_pagination__tittle") var title: String?,
    @ColumnInfo(name = "imdb_movie_popular_pagination__poster_path") var poster_path: String?
)

fun MovieLocalPopularPaginationEntity.mapToDomain(): MovieLocalPopularPaginationData =
        MovieLocalPopularPaginationData(
                id,
                title,
                poster_path
        )

fun List<MovieLocalPopularPaginationEntity>.mapToDomain(): List<MovieLocalPopularPaginationData> = map { it?.mapToDomain() }

fun Flow<List<MovieLocalPopularPaginationEntity>>.mapToDomain(): Flow<List<MovieLocalPopularPaginationData>> = map { it.mapToDomain() }

fun LiveData<List<MovieLocalPopularPaginationEntity>>.mapToDomain(): LiveData<List<MovieLocalPopularPaginationData>> = map { it.mapToDomain() }