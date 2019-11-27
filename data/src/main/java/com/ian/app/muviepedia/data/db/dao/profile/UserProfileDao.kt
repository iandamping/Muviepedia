package com.ian.app.muviepedia.data.db.dao.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.UserProfileEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 05/10/2019.
Github = https://github.com/iandamping
 */
@Dao
interface UserProfileDao {
    @Query("SELECT * FROM movie_userdata")
    fun loadAll(): Flow<UserProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputUser: UserProfileEntity)

    @Query("DELETE FROM movie_userdata")
    suspend fun deleteAllData()
}