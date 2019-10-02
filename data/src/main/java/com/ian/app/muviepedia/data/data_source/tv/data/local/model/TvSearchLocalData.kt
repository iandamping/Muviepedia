package com.ian.app.muviepedia.data.data_source.tv.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_search_data")
data class TvSearchLocalData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_tv_search_id") var id: Int?,
        @ColumnInfo(name = "imdb_tv_search_name") var name: String?,
        @ColumnInfo(name = "imdb_tv_search_poster_path") var poster_path: String?
)