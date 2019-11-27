package com.ian.app.muviepedia.data.cleanarch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ian.app.muviepedia.data.cleanarch.data.datasource.UserProfileCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.UserProfileRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.mapToData
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.mapToDomain
import com.ian.app.muviepedia.model.profile.UserProfileData
import com.ian.app.muviepedia.repository.UserRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class UserRepositoryImpl(
    private val cacheDataSource: UserProfileCacheDataSource,
    private val remoteDataSource: UserProfileRemoteDataSource
) : UserRepository {
    override fun get(): LiveData<UserProfileData> {
        return liveData(Dispatchers.IO) {
            val cacheData = cacheDataSource.getCache().mapToDomain()
            emitSource(cacheData.asLiveData())
            cacheDataSource.setCache(remoteDataSource.get().mapToData())
        }
    }

    override fun initOnResume() {
       remoteDataSource.initOnResume()
    }

    override fun initOnPause() {
      remoteDataSource.initOnPause()
    }

    override suspend fun initLogout() {
        remoteDataSource.initLogout()
    }
}