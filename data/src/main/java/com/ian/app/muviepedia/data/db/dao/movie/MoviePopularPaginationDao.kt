package com.ian.app.muviepedia.data.db.dao.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularPaginationEntity

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MoviePopularPaginationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieLocalPopularPaginationEntity>)

    @Query("SELECT * FROM movie_popular_pagination_data")
    fun loadAllPagination(): DataSource.Factory<Int, MovieLocalPopularPaginationEntity>
}