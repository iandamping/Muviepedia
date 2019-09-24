package com.ian.junemon.spe_learning_mvvm.movie.data.remote.pagination.upcoming

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.movie.data.local.dao.MovieUpComingPaginationDao
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MovieUpComingPaginationData
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class MovieUpComingRemoteDataSourceFactory(
        private val dataSource: MovieRemoteDataSource,
        private val dao: MovieUpComingPaginationDao,
        private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieUpComingPaginationData>() {

    override fun create(): DataSource<Int, MovieUpComingPaginationData> {
        return MovieUpComingRemotePagingDataSource(dataSource, dao, scope)
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