package com.ian.app.muviepedia.data.data_source.movie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_search_data")
data class MovieSearchLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_movie_search_id") var id: Int?,
        @ColumnInfo(name = "imdb_movie_search_tittle") var title: String?,
        @ColumnInfo(name = "imdb_movie_search_poster_path") var poster_path: String?
)