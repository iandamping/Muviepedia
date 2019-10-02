package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model.MovieNowPlayingLocalData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieNowPlayingDao {

    @Query("SELECT * FROM movie_now_playing_data")
    fun loadAll(): LiveData<List<MovieNowPlayingLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieNowPlayingLocalData>)

    @Query("DELETE FROM movie_now_playing_data")
    fun deleteAllData()

}