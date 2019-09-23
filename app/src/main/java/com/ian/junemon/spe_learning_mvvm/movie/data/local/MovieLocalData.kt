package com.ian.junemon.spe_learning_mvvm.movie.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_data")
data class MovieLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_type") var type: String?,
        @ColumnInfo(name = "imdb_movie_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_poster_path") var poster_path: String?
)

/*@Entity(tableName = "movie_now_playing_data")
data class MovieNowPlayingLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_now_playing_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_now_playing_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_now_playing_poster_path") var poster_path: String?
)

@Entity(tableName = "movie_upcoming_data")
data class MovieUpComingLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_upcoming_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_upcoming_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_upcoming_poster_path") var poster_path: String?
)

@Entity(tableName = "movie_popular_data")
data class MoviePopularLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_popular_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_popular_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_popular_poster_path") var poster_path: String?
)*/
