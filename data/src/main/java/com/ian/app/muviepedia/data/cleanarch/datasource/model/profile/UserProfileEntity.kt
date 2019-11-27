package com.ian.app.muviepedia.data.cleanarch.datasource.model.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.profile.UserProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "movie_userdata")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true) var localID: Int?,
    @ColumnInfo(name = "imdb_movie_userdata_movie_id") var userID: String?,
    @ColumnInfo(name = "imdb_movie_userdata_movie_photo") var photoUser: String?,
    @ColumnInfo(name = "imdb_movie_userdata_movie_name") var nameUser: String?,
    @ColumnInfo(name = "imdb_movie_userdata_movie_email") var emailUser: String?
)

fun UserProfileData.mapToData() = UserProfileEntity(localID, userID, photoUser, nameUser, emailUser)

fun UserProfileEntity.mapToDomain() = UserProfileData(localID, userID, photoUser, nameUser, emailUser)

fun Flow<UserProfileEntity>.mapToDomain() = map { it?.mapToDomain() }