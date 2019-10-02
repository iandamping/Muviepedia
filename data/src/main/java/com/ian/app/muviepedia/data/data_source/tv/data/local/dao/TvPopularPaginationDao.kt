package com.ian.app.muviepedia.data.data_source.tv.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.TvPopularPaginationData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvPopularPaginationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvPopularPaginationData>)

    @Query("SELECT * FROM tv_popular_pagination_data")
    fun loadAllPagination(): DataSource.Factory<Int, TvPopularPaginationData>
}