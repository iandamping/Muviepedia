package com.ian.app.muviepedia.data.cleanarch.datasource.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.movie.MovieLocalNowPlayingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_now_playing_data")
data class MovieLocalNowPlayingEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_movie_now_playing_id") var id: Int?,
    @ColumnInfo(name = "imdb_movie_now_playing_tittle") var title: String?,
    @ColumnInfo(name = "imdb_movie_now_playing_poster_path") var poster_path: String?
)

fun MovieLocalNowPlayingEntity.mapToDomain(): MovieLocalNowPlayingData = MovieLocalNowPlayingData(id, title, poster_path)

fun List<MovieLocalNowPlayingEntity>.mapToDomain(): List<MovieLocalNowPlayingData> = map { it?.mapToDomain() }

fun Flow<List<MovieLocalNowPlayingEntity>>.mapToDomain(): Flow<List<MovieLocalNowPlayingData>> = map { it.mapToDomain() }