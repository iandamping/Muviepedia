package com.ian.app.muviepedia.data.db.dao.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieUpComingDao {

    @Query("SELECT * FROM movie_upcoming_data")
    fun loadAll(): Flow<List<MovieLocalUpComingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieLocalUpComingEntity>)

    @Query("DELETE FROM movie_upcoming_data")
    fun deleteAllData()
}