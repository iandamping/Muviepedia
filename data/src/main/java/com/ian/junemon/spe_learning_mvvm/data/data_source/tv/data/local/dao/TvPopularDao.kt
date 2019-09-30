package com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvPopularData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvPopularDao {

    @Query("SELECT * FROM tv_popular_data")
    fun loadAll(): LiveData<List<TvPopularData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvPopularData>)

    @Query("DELETE FROM tv_popular_data")
    fun deleteAllData()
}