package com.ian.junemon.spe_learning_mvvm.movie.data.remote

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalDao
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalData
import kotlinx.coroutines.CoroutineScope

/**
 *
Created by Ian Damping on 23/09/2019.
Github = https://github.com/iandamping
 */
class MovieRemoteDataSourceFactory(
        private val movieType: String,
        private val dataSource: MovieRemoteDataSource,
        private val dao: MovieLocalDao,
        private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieLocalData>() {
//    private val liveData = MutableLiveData<MovieRemotePagingDataSource>()

    override fun create(): DataSource<Int, MovieLocalData> {
        //        liveData.postValue(source)
        return MovieRemotePagingDataSource(movieType, dataSource, dao, scope)
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