package com.ian.junemon.spe_learning_mvvm.movie.data.remote.pagination.popular

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.movie.data.local.dao.MoviePopularPaginationDao
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MoviePopularPaginationData
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 23/09/2019.
Github = https://github.com/iandamping
 */
class MoviePopularRemoteDataSourceFactory(
        private val dataSource: MovieRemoteDataSource,
        private val dao: MoviePopularPaginationDao,
        private val scope: CoroutineScope
) : DataSource.Factory<Int, MoviePopularPaginationData>() {

    override fun create(): DataSource<Int, MoviePopularPaginationData> {
        return MoviePopularRemotePagingDataSource(dataSource, dao, scope)
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