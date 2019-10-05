package com.ian.app.muviepedia.data.data_source.profile.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.data_source.profile.local.model.UserProfileData

/**
 *
Created by Ian Damping on 05/10/2019.
Github = https://github.com/iandamping
 */
@Dao
interface UserProfileDao {
    @Query("SELECT * FROM movie_userdata")
    fun loadAll(): LiveData<UserProfileData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputUser: UserProfileData)

    @Query("DELETE FROM movie_userdata")
    suspend fun deleteAllData()
}