package com.ian.app.muviepedia.data.data_source.tv.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_popular_data")
data class TvPopularData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_tv_popular_id") var id: Int?,
        @ColumnInfo(name = "imdb_tv_popular_tittle") var name: String?,
        @ColumnInfo(name = "imdb_tv_popular_poster_path") var poster_path: String?)