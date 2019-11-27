package com.ian.app.muviepedia.data.cleanarch.datasource.cache

import com.ian.app.muviepedia.data.MovieDatabase
import com.ian.app.muviepedia.data.cleanarch.data.datasource.UserProfileCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.UserProfileEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class UserCacheDataSourceImpl(private val db: MovieDatabase) : UserProfileCacheDataSource {
    override suspend fun setCache(data: UserProfileEntity) {
        db.userProfileDao().insertAll(data)
    }

    override fun getCache(): Flow<UserProfileEntity> {
       return db.userProfileDao().loadAll()
    }

    override suspend fun deleteCache() {
       db.userProfileDao().deleteAllData()
    }
}