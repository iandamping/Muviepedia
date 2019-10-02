package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model.MoviePopularLocalData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MoviePopularDao {

    @Query("SELECT * FROM movie_popular_data")
    fun loadAll(): LiveData<List<MoviePopularLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MoviePopularLocalData>)

    @Query("DELETE FROM movie_popular_data")
    fun deleteAllData()
}