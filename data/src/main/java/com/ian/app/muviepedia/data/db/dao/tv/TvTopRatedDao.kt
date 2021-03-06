package com.ian.app.muviepedia.data.db.dao.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvTopRatedDao {
    @Query("SELECT * FROM tv_toprated_data")
    fun loadAll(): Flow<List<TvLocalTopRatedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvLocalTopRatedEntity>)

    @Query("DELETE FROM tv_toprated_data")
    fun deleteAllData()
}