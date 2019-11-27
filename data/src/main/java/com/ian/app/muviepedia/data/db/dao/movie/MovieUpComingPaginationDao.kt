package com.ian.app.muviepedia.data.db.dao.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingPaginationEntity

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieUpComingPaginationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieLocalUpComingPaginationEntity>)

    @Query("SELECT * FROM movie_upcoming_pagination_data")
    fun loadAllPagination(): DataSource.Factory<Int, MovieLocalUpComingPaginationEntity>
}