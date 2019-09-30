package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model.MovieSaveDetailData

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieSaveDetailDao {

    @Query("SELECT * FROM movie_save_detail_data")
    fun loadAll(): LiveData<List<MovieSaveDetailData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: MovieSaveDetailData)

    @Query("DELETE FROM movie_save_detail_data")
    fun deleteAllData()

    @Query("DELETE FROM movie_save_detail_data where localID = :selectedId")
    suspend fun deleteSelectedId(selectedId: Int)
}