package com.ian.app.muviepedia.data.data_source.profile.local

import com.ian.app.muviepedia.data.MovieDatabase
import com.ian.app.muviepedia.data.data_source.profile.local.model.UserProfileData

/**
 *
Created by Ian Damping on 05/10/2019.
Github = https://github.com/iandamping
 */
class UserProfileDataSource(private val db: MovieDatabase) {

    val getUserData by lazy { db.userProfileDao().loadAll() }

    suspend fun saveUserData(data:UserProfileData) = db.userProfileDao().insertAll(data)

    suspend fun clearAllUserData() = db.userProfileDao().deleteAllData()
}