package com.ian.app.muviepedia.data.cleanarch.datasource.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ian.app.muviepedia.model.tvshow.TvLocalSaveDetailData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
@Entity(tableName = "tv_save_detail_data")
data class TvLocalSaveDetailEntity(
    @PrimaryKey(autoGenerate = true) var localID: Int?,
    @ColumnInfo(name = "tv_save_detail_data_id") var id: Int?,
    @ColumnInfo(name = "tv_save_detail_data_release_date") var number_of_episodes: Int?,
    @ColumnInfo(name = "tv_save_detail_data_runtime") var first_air_date: String?,
    @ColumnInfo(name = "tv_save_detail_data_vote_average") var vote_average: Double?,
    @ColumnInfo(name = "tv_save_detail_data_title") var name: String?,
    @ColumnInfo(name = "tv_save_detail_data_tagline") var original_name: String?,
    @ColumnInfo(name = "tv_save_detail_data_overview") var overview: String?,
    @ColumnInfo(name = "tv_save_detail_poster_path") var poster_path: String?
)
fun TvLocalSaveDetailData.mapToDatabase(): TvLocalSaveDetailEntity = TvLocalSaveDetailEntity(localID, id, number_of_episodes, first_air_date, vote_average, name, original_name, overview, poster_path)

fun TvLocalSaveDetailEntity.mapToDomain(): TvLocalSaveDetailData = TvLocalSaveDetailData(localID, id, number_of_episodes, first_air_date, vote_average, name, original_name, overview, poster_path)

fun List<TvLocalSaveDetailEntity>.mapToDomain(): List<TvLocalSaveDetailData> = map { it?.mapToDomain() }

fun Flow<List<TvLocalSaveDetailEntity>>.mapToDomain(): Flow<List<TvLocalSaveDetailData>> = map { it.mapToDomain() }

fun Flow<TvLocalSaveDetailEntity>.mapToSingleDomain(): Flow<TvLocalSaveDetailData> = map { it.mapToDomain() }