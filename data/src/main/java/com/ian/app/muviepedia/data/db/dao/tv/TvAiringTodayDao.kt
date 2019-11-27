package com.ian.app.muviepedia.data.db.dao.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalAiringTodayEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvAiringTodayDao {

    @Query("SELECT * FROM tv_airing_data")
    fun loadAll(): Flow<List<TvLocalAiringTodayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvLocalAiringTodayEntity>)

    @Query("DELETE FROM tv_airing_data")
    fun deleteAllData()
}