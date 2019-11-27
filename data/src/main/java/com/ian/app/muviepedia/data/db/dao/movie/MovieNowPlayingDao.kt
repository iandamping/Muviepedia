package com.ian.app.muviepedia.data.db.dao.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalNowPlayingEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieNowPlayingDao {

    @Query("SELECT * FROM movie_now_playing_data")
    fun loadAll(): Flow<List<MovieLocalNowPlayingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieLocalNowPlayingEntity>)

    @Query("DELETE FROM movie_now_playing_data")
    fun deleteAllData()
}