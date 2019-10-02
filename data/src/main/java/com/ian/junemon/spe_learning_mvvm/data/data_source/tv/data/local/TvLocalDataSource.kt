package com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local

import com.ian.junemon.spe_learning_mvvm.data.MovieDatabase
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvPopularData
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvSaveDetailData
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvSearchLocalData
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvTopRatedData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvAiringTodayData

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
class TvLocalDataSource(private val db: MovieDatabase) {
    val getDetailSavedTvData by lazy { db.tvSaveDetailDao().loadAll() }

    val getSearchTvData by lazy { db.tvSearchDao().loadAll() }

    val getTopRatedTvData by lazy { db.tvTopRatedDao().loadAll() }

    val getAiringTodayTvData by lazy { db.tvAiringTodayDao().loadAll() }

    val getPopularTvData by lazy { db.tvPopularDao().loadAll() }

    val popularTvPaginationDao by lazy { db.tvPopularPaginationDao() }

    val getPopularTvPaginationData by lazy { db.tvPopularPaginationDao().loadAllPagination() }

    val topRatedTvPaginationDao by lazy { db.tvTopRatedPaginationDao() }

    val getTopRatedTvPaginationData by lazy { db.tvTopRatedPaginationDao().loadAllPagination() }

    suspend fun saveDetailTvData(data: TvSaveDetailData) = db.tvSaveDetailDao().insertAll(data)

    suspend fun saveAiringTodayTv(data: List<TvAiringTodayData>) = db.tvAiringTodayDao().insertAll(data)

    suspend fun savePopularTv(data: List<TvPopularData>) = db.tvPopularDao().insertAll(data)

    suspend fun saveTopRatedTv(data: List<TvTopRatedData>) = db.tvTopRatedDao().insertAll(data)

    suspend fun updateSearchTv(data: List<TvSearchLocalData>) = db.tvSearchDao().updateData(data)

    suspend fun deleteSelectedDetailTvData(selectedId: Int) = db.tvSaveDetailDao().deleteSelectedId(selectedId)

    suspend fun clearSearchTv() = db.tvSaveDetailDao().deleteAllData()
}