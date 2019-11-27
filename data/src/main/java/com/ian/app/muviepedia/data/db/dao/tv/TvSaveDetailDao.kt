package com.ian.app.muviepedia.data.db.dao.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSaveDetailEntity
import kotlinx.coroutines.flow.Flow

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
@Dao
interface TvSaveDetailDao {
    @Query("SELECT * FROM tv_save_detail_data")
    fun loadAll(): Flow<List<TvLocalSaveDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inputMovie: TvLocalSaveDetailEntity)

    @Query("SELECT * FROM tv_save_detail_data WHERE localID = :id")
    fun loadAllTvShowDataById(id: Int): Flow<TvLocalSaveDetailEntity>

    @Query("DELETE FROM tv_save_detail_data")
    suspend fun deleteAllData()

    @Query("DELETE FROM tv_save_detail_data where tv_save_detail_data_id = :selectedId")
    suspend fun deleteSelectedId(selectedId: Int)
}