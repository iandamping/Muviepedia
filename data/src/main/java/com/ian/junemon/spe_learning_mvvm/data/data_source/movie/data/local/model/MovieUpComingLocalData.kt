package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_upcoming_data")
data class MovieUpComingLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_upcoming_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_upcoming_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_upcoming_poster_path") var poster_path: String?
)