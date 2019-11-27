package com.ian.app.muviepedia.data.db.dao.tv

import androidx.room.*
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSearchEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvSearchDao {

    @Query("SELECT * FROM tv_search_data")
    fun loadAll(): Flow<List<TvLocalSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: List<TvLocalSearchEntity>)

    @Transaction
    suspend fun updateData(inputMovie: List<TvLocalSearchEntity>) {
        deleteAllData()
        insertAll(inputMovie)
    }

    @Query("DELETE FROM tv_search_data")
    suspend fun deleteAllData()
}