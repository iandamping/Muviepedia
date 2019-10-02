package com.ian.app.muviepedia.tv.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.tv.data.local.model.TvAiringTodayData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvAiringTodayDao {

    @Query("SELECT * FROM tv_airing_data")
    fun loadAll(): LiveData<List<TvAiringTodayData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvAiringTodayData>)

    @Query("DELETE FROM tv_airing_data")
    fun deleteAllData()
}