package com.ian.junemon.spe_learning_mvvm.movie.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieLocalDao {
    @Query("SELECT * FROM movie_data")
    fun loadAll(): LiveData<List<MovieLocalData>>

    @Query("SELECT * FROM movie_data WHERE imdb_movie_type = :movieType")
    fun loadAllPagination(movieType: String): DataSource.Factory<Int, MovieLocalData>

    @Query("SELECT * FROM movie_data where imdb_movie_type = :selectedCategory ")
    fun loadSelectedCategory(selectedCategory: String): LiveData<List<MovieLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieLocalData>)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpComing(inputMovie: List<MovieUpComingLocalData>)*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(inputMovie: List<MovieNowPlayingLocalData>)*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopular(inputMovie: List<MoviePopularLocalData>)*/

    @Query("DELETE FROM movie_data")
    fun deleteAllData()

}