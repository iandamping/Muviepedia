package com.ian.junemon.spe_learning_mvvm.tv.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvTopRatedData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvTopRatedDao {
    @Query("SELECT * FROM tv_toprated_data")
    fun loadAll(): LiveData<List<TvTopRatedData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvTopRatedData>)

    @Query("DELETE FROM tv_toprated_data")
    fun deleteAllData()
}