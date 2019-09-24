package com.ian.junemon.spe_learning_mvvm.movie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MovieUpComingLocalData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieUpComingDao {

    @Query("SELECT * FROM movie_upcoming_data")
    fun loadAll(): LiveData<List<MovieUpComingLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieUpComingLocalData>)

    @Query("DELETE FROM movie_upcoming_data")
    fun deleteAllData()
}