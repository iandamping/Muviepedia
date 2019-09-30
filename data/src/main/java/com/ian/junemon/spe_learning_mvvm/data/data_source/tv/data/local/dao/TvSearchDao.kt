package com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvSearchLocalData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvSearchDao {

    @Query("SELECT * FROM tv_search_data")
    fun loadAll(): LiveData<List<TvSearchLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvSearchLocalData>)

    @Transaction
    suspend fun updateData(inputMovie: List<TvSearchLocalData>) {
        deleteAllData()
        insertAll(inputMovie)
    }

    @Query("DELETE FROM tv_search_data")
    suspend fun deleteAllData()
}