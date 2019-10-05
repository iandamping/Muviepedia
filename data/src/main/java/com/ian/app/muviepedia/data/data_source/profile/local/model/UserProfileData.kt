package com.ian.app.muviepedia.data.data_source.profile.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_userdata")
data class UserProfileData(
        @PrimaryKey(autoGenerate = true) var localID: Int?,
        @ColumnInfo(name = "imdb_movie_userdata_movie_id") var userID: String?,
        @ColumnInfo(name = "imdb_movie_userdata_movie_photo") var photoUser: String?,
        @ColumnInfo(name = "imdb_movie_userdata_movie_name") var nameUser: String?,
        @ColumnInfo(name = "imdb_movie_userdata_movie_email") var emailUser: String?)