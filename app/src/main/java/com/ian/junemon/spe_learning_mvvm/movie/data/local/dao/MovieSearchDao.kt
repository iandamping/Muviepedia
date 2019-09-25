package com.ian.junemon.spe_learning_mvvm.movie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MoviePopularLocalData
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MovieSearchLocalData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieSearchDao {

    @Query("SELECT * FROM movie_search_data")
    fun loadAll(): LiveData<List<MovieSearchLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieSearchLocalData>)

    @Transaction
    suspend fun updateData(inputMovie: List<MovieSearchLocalData>) {
        deleteAllData()
        insertAll(inputMovie)
    }

    @Query("DELETE FROM movie_search_data")
    fun deleteAllData()
}