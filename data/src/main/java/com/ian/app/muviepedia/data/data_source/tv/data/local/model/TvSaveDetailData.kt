package com.ian.app.muviepedia.data.data_source.tv.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_save_detail_data")
data class TvSaveDetailData(
        @PrimaryKey(autoGenerate = true) var localID: Int?,
        @ColumnInfo(name = "tv_save_detail_data_id") var id: Int?,
        @ColumnInfo(name = "tv_save_detail_data_release_date") var number_of_episodes: Int?,
        @ColumnInfo(name = "tv_save_detail_data_runtime") var first_air_date: String?,
        @ColumnInfo(name = "tv_save_detail_data_vote_average") var vote_average: Double?,
        @ColumnInfo(name = "tv_save_detail_data_title") var name: String?,
        @ColumnInfo(name = "tv_save_detail_data_tagline") var original_name: String?,
        @ColumnInfo(name = "tv_save_detail_data_overview") var overview: String?,
        @ColumnInfo(name = "tv_save_detail_poster_path") var poster_path: String?
)