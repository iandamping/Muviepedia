package com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_toprated_pagination_data")
data class TvTopRatedPaginationData(
        @PrimaryKey
        @ColumnInfo(name = "imdb_tv_toprated_pagination_id") var id: Int?,
        @ColumnInfo(name = "imdb_tv_toprated_pagination_tittle") var name: String?,
        @ColumnInfo(name = "imdb_tv_toprated_pagination_poster_path") var poster_path: String?
)