package com.ian.app.muviepedia.data.db.dao.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSaveDetailEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieSaveDetailDao {

    @Query("SELECT * FROM movie_save_detail_data")
    fun loadAll(): Flow<List<MovieLocalSaveDetailEntity>>

    @Query("SELECT * FROM movie_save_detail_data WHERE localID = :id")
    fun loadAllMovieDataById(id: Int): Flow<MovieLocalSaveDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: MovieLocalSaveDetailEntity)

    @Query("DELETE FROM movie_save_detail_data")
    suspend fun deleteAllData()

    @Query("DELETE FROM movie_save_detail_data where movie_save_detail_data_id = :selectedId")
    suspend fun deleteSelectedId(selectedId: Int)
}