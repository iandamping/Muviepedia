package com.ian.junemon.spe_learning_mvvm.tv.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvTopRatedPaginationData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvTopRatedPaginationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvTopRatedPaginationData>)

    @Query("SELECT * FROM tv_toprated_pagination_data")
    fun loadAllPagination(): DataSource.Factory<Int, TvTopRatedPaginationData>
}