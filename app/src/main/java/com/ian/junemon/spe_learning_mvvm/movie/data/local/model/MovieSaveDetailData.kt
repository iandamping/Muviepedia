package com.ian.junemon.spe_learning_mvvm.movie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_save_detail_data")
data class MovieSaveDetailData(
        @PrimaryKey(autoGenerate = true) var localID: Int?,
        @ColumnInfo(name = "movie_save_detail_data_id") var id: Int?,
        @ColumnInfo(name = "movie_save_detail_data_budget") var budget: Int?,
        @ColumnInfo(name = "movie_save_detail_data_revenue") var revenue: String?,
        @ColumnInfo(name = "movie_save_detail_data_release_date") var release_date: String?,
        @ColumnInfo(name = "movie_save_detail_data_runtime") var runtime: Int?,
        @ColumnInfo(name = "movie_save_detail_data_vote_average") var vote_average: Double?,
        @ColumnInfo(name = "movie_save_detail_data_title") var title: String?,
        @ColumnInfo(name = "movie_save_detail_data_tagline") var tagline: String?,
        @ColumnInfo(name = "movie_save_detail_data_overview") var overview: String?,
        @ColumnInfo(name = "movie_save_detail_poster_path") var poster_path: String?)