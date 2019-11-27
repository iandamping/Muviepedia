package com.ian.app.muviepedia.data.cleanarch.data.datasource

import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.UserProfileEntity
import com.ian.app.muviepedia.model.profile.UserProfileData
import kotlinx.coroutines.flow.Flow

interface UserProfileCacheDataSource {

   suspend fun setCache(data: UserProfileEntity)

    fun getCache(): Flow<UserProfileEntity>

    suspend fun deleteCache()
}

interface UserProfileRemoteDataSource {

    suspend fun get(): UserProfileData

    fun initOnResume()

    fun initOnPause()

    suspend fun initLogout()
}