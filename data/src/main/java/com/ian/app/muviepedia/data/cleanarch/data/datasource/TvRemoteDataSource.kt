package com.ian.app.muviepedia.data.cleanarch.data.datasource

import androidx.paging.DataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalAiringTodayEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSaveDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedPaginationEntity
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muviepedia.model.tvshow.TvLocalAiringTodayData
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularData
import com.ian.app.muviepedia.model.tvshow.TvLocalSaveDetailData
import com.ian.app.muviepedia.model.tvshow.TvLocalSearchData
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedData
import com.ian.app.muviepedia.model.tvshow.TvRemoteData
import com.ian.app.muviepedia.model.tvshow.TvRemoteDetailData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 25,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface TvRemoteDataSource {
    suspend fun getRemoteAiringTodayTv(): ResultToConsume<List<TvLocalAiringTodayEntity>>

    suspend fun getRemotePopularTv(): ResultToConsume<List<TvLocalPopularEntity>>

    suspend fun getRemoteTopRatedTv(): ResultToConsume<List<TvLocalTopRatedEntity>>

    suspend fun getRemoteDetailTv(movieId: Int): ResultToConsume<TvRemoteDetailData>

    suspend fun getRemoteSimilarTV(movieId: Int): ResultToConsume<List<TvRemoteData>>

    suspend fun getRemoteSearchTv(querry: String): ResultToConsume<List<TvLocalSearchEntity>>

    suspend fun getRemotePaginationPopularTv(page: Int): ResultToConsume<List<TvLocalPopularPaginationEntity>>

    suspend fun getRemotePaginationTopRatedTv(page: Int): ResultToConsume<List<TvLocalTopRatedPaginationEntity>>
}

interface TvCacheDataSource {

    suspend fun setCacheAiringTodayTv(data: List<TvLocalAiringTodayEntity>)

    suspend fun setCachePopularTv(data: List<TvLocalPopularEntity>)

    suspend fun setCacheTopRatedTv(data: List<TvLocalTopRatedEntity>)

    suspend fun setCacheDetailTv(data: TvLocalSaveDetailEntity)

    suspend fun setCacheSearchTv(data: List<TvLocalSearchEntity>)

    suspend fun setCachePaginationPopularTv(inputMovie: List<TvLocalPopularPaginationEntity>)

    suspend fun setCachePaginationTopRatedTv(inputMovie: List<TvLocalTopRatedPaginationEntity>)

    fun getCacheSingleDetailMovie(localSelectedId: Int): Flow<TvLocalSaveDetailData>

    fun getCacheAllSingleDetailMovie(): Flow<List<TvLocalSaveDetailData>>

    fun getCacheAiringTodayTv(): Flow<List<TvLocalAiringTodayData>>

    fun getCachePopularTv(): Flow<List<TvLocalPopularData>>

    fun getCacheTopRatedTv(): Flow<List<TvLocalTopRatedData>>

    fun getCacheSearchTv(): Flow<List<TvLocalSearchData>>

    fun getcachePaginationPopularTv(): DataSource.Factory<Int, TvLocalPopularPaginationEntity>

    fun getCachePaginationTopRatedTv(): DataSource.Factory<Int, TvLocalTopRatedPaginationEntity>

    suspend fun deleteAllDetailCache()

    suspend fun deleteSelectedDetailCache(localId: Int)

    suspend fun clearSearchTv()
}