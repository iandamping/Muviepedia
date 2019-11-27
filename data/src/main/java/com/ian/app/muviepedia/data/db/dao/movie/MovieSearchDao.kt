package com.ian.app.muviepedia.data.db.dao.movie

import androidx.room.*
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSearchEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface MovieSearchDao {

    @Query("SELECT * FROM movie_search_data")
    fun loadAll(): Flow<List<MovieLocalSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<MovieLocalSearchEntity>)

    @Transaction
    suspend fun updateData(inputMovie: List<MovieLocalSearchEntity>) {
        deleteAllData()
        insertAll(inputMovie)
    }

    @Query("DELETE FROM movie_search_data")
    suspend fun deleteAllData()
}