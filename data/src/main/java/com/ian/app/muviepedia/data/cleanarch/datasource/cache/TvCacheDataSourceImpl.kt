package com.ian.app.muviepedia.data.cleanarch.datasource.cache

import androidx.paging.DataSource
import com.ian.app.muviepedia.data.MovieDatabase
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalAiringTodayEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSaveDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToDomain
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToSingleDomain
import com.ian.app.muviepedia.model.tvshow.TvLocalAiringTodayData
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularData
import com.ian.app.muviepedia.model.tvshow.TvLocalSaveDetailData
import com.ian.app.muviepedia.model.tvshow.TvLocalSearchData
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvCacheDataSourceImpl(private val db: MovieDatabase) : TvCacheDataSource {
    override suspend fun setCacheAiringTodayTv(data: List<TvLocalAiringTodayEntity>) {
        db.tvAiringTodayDao().insertAll(data)
    }

    override suspend fun setCachePopularTv(data: List<TvLocalPopularEntity>) {
        db.tvPopularDao().insertAll(data)
    }

    override suspend fun setCacheTopRatedTv(data: List<TvLocalTopRatedEntity>) {
        db.tvTopRatedDao().insertAll(data)
    }

    override suspend fun setCacheDetailTv(data: TvLocalSaveDetailEntity) {
        db.tvSaveDetailDao().insertAll(data)
    }

    override suspend fun setCacheSearchTv(data: List<TvLocalSearchEntity>) {
        db.tvSearchDao().updateData(data)
    }

    override suspend fun setCachePaginationPopularTv(inputMovie: List<TvLocalPopularPaginationEntity>) {
        db.tvPopularPaginationDao().insertAll(inputMovie)
    }

    override suspend fun setCachePaginationTopRatedTv(inputMovie: List<TvLocalTopRatedPaginationEntity>) {
        db.tvTopRatedPaginationDao().insertAll(inputMovie)
    }

    override fun getCacheSingleDetailMovie(localSelectedId: Int): Flow<TvLocalSaveDetailData> {
        return db.tvSaveDetailDao().loadAllTvShowDataById(localSelectedId).mapToSingleDomain()
    }

    override fun getCacheAllSingleDetailMovie(): Flow<List<TvLocalSaveDetailData>> {
        return db.tvSaveDetailDao().loadAll().mapToDomain()
    }

    override fun getCacheAiringTodayTv(): Flow<List<TvLocalAiringTodayData>> {
        return db.tvAiringTodayDao().loadAll().mapToDomain()
    }

    override fun getCachePopularTv(): Flow<List<TvLocalPopularData>> {
        return db.tvPopularDao().loadAll().mapToDomain()
    }

    override fun getCacheTopRatedTv(): Flow<List<TvLocalTopRatedData>> {
        return db.tvTopRatedDao().loadAll().mapToDomain()
    }

    override fun getCacheSearchTv(): Flow<List<TvLocalSearchData>> {
        return db.tvSearchDao().loadAll().mapToDomain()
    }

    override fun getcachePaginationPopularTv(): DataSource.Factory<Int, TvLocalPopularPaginationEntity> {
        return db.tvPopularPaginationDao().loadAllPagination()
    }

    override fun getCachePaginationTopRatedTv(): DataSource.Factory<Int, TvLocalTopRatedPaginationEntity> {
        return db.tvTopRatedPaginationDao().loadAllPagination()
    }

    override suspend fun deleteAllDetailCache() {
        db.tvSaveDetailDao().deleteAllData()
    }

    override suspend fun deleteSelectedDetailCache(localId: Int) {
        db.tvSaveDetailDao().deleteSelectedId(localId)
    }

    override suspend fun clearSearchTv() {
        db.tvSearchDao().deleteAllData()
    }
}