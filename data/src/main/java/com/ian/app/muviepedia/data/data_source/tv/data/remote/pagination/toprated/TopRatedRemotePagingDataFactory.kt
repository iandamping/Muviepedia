package com.ian.app.muviepedia.data.data_source.tv.data.remote.pagination.toprated

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.data_source.tv.data.local.dao.TvTopRatedPaginationDao
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.TvTopRatedPaginationData
import com.ian.app.muviepedia.data.data_source.tv.data.remote.TvRemoteDataSource
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TopRatedRemotePagingDataFactory(private val dataSource: TvRemoteDataSource,
                                      private val dao: TvTopRatedPaginationDao,
                                      private val scope: CoroutineScope) : DataSource.Factory<Int, TvTopRatedPaginationData>() {
    override fun create(): DataSource<Int, TvTopRatedPaginationData> {
        return TopRatedRemotePagingDataSource(dataSource, dao, scope)
    }

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig(): PagedList.Config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()
    }
}