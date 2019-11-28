package com.ian.app.muviepedia.data.cleanarch.data.repository.paginationfactory.movie

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.repository.pagination.movie.MoviePaginationUpComingRepository
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingPaginationEntity
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MoviePaginationUpComingFactory(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieCacheDataSource
) : DataSource.Factory<Int, MovieLocalUpComingPaginationEntity>() {

    override fun create(): DataSource<Int, MovieLocalUpComingPaginationEntity> {
        return MoviePaginationUpComingRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
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