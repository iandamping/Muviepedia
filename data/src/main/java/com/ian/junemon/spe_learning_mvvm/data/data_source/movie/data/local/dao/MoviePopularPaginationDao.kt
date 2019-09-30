package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model.MoviePopularPaginationData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MoviePopularPaginationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MoviePopularPaginationData>)

    @Query("SELECT * FROM movie_popular_pagination_data")
    fun loadAllPagination(): DataSource.Factory<Int, MoviePopularPaginationData>
}