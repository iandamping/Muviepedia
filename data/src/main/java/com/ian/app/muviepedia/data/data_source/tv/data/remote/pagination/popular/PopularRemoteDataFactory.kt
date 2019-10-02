package com.ian.app.muviepedia.data.data_source.tv.data.remote.pagination.popular

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.data_source.tv.data.local.dao.TvPopularPaginationDao
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.TvPopularPaginationData
import com.ian.app.muviepedia.data.data_source.tv.data.remote.TvRemoteDataSource
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class PopularRemoteDataFactory(private val dataSource: TvRemoteDataSource,
                               private val dao: TvPopularPaginationDao,
                               private val scope: CoroutineScope) : DataSource.Factory<Int, TvPopularPaginationData>() {
    override fun create(): DataSource<Int, TvPopularPaginationData> {
        return PopularRemotePagingDataSource(dataSource, dao, scope)
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