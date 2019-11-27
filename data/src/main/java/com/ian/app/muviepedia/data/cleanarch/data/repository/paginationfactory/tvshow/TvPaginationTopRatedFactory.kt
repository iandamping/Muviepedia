package com.ian.app.muviepedia.data.cleanarch.data.repository.paginationfactory.tvshow

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.repository.pagination.tvshow.TvPaginationTopRatedRepository
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedPaginationEntity
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvPaginationTopRatedFactory(
    private val remoteDataSource: TvRemoteDataSource,
    private val localDataSource: TvCacheDataSource,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, TvLocalTopRatedPaginationEntity>() {

    override fun create(): DataSource<Int, TvLocalTopRatedPaginationEntity> {
        return TvPaginationTopRatedRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            scope = scope
        )
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