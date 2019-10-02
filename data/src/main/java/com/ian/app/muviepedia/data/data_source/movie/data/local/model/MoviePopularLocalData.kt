package com.ian.app.muviepedia.data.data_source.movie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_popular_data")
data class MoviePopularLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_popular_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_popular_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_popular_poster_path") var poster_path: String?
)