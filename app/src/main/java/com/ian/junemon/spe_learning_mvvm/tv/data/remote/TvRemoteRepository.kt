package com.ian.junemon.spe_learning_mvvm.tv.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.data.MovieDatabase
import com.ian.junemon.spe_learning_mvvm.data.resultLiveData
import com.ian.junemon.spe_learning_mvvm.data.searchResultLiveData
import com.ian.junemon.spe_learning_mvvm.data.singleResultLiveData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvPopularPaginationData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvTopRatedPaginationData
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.pagination.popular.PopularRemoteDataFactory
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.pagination.toprated.TopRatedRemotePagingDataFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TvRemoteRepository(private val remoteSource: TvRemoteDataSource, private val db: MovieDatabase) {

    fun observeAiringToday(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { db.tvAiringTodayDao().loadAll() },
            networkCall = { remoteSource.getAiringTodayTv() },
            saveCallResult = { db.tvAiringTodayDao().insertAll(it.results.toAiringToday(scope)) }
    ).distinctUntilChanged()

    fun observePopular(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { db.tvPopularDao().loadAll() },
            networkCall = { remoteSource.getPopularTv() },
            saveCallResult = { db.tvPopularDao().insertAll(it.results.toPopularTv(scope)) }
    ).distinctUntilChanged()

    fun observeTopRated(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { db.tvTopRatedDao().loadAll() },
            networkCall = { remoteSource.getPopularTv() },
            saveCallResult = { db.tvTopRatedDao().insertAll(it.results.toTopRatedTv(scope)) }
    ).distinctUntilChanged()

    fun getDetailTv(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getDetailTv(movieId) }
    )

    fun getSimilarTv(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getSimilarTv(movieId) }
    )

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun observeSearchMovie(querry: String) = searchResultLiveData(querry) {
        remoteSource.getSearchTv(querry)
    }.distinctUntilChanged()


    fun observePopularTvPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePopularPaginationPaged(scope)
            else observeLocalPopularPaginationPaged()


    private fun observeRemotePopularPaginationPaged(scope: CoroutineScope): LiveData<PagedList<TvPopularPaginationData>> {
        val dataSourceFactory = PopularRemoteDataFactory(remoteSource, db.tvPopularPaginationDao(), scope)
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }

    private fun observeLocalPopularPaginationPaged(): LiveData<PagedList<TvPopularPaginationData>> {
        val dataSourceFactory = db.tvPopularPaginationDao().loadAllPagination()
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }


    fun observeTopRatedPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePopularPaginationPaged(scope)
            else observeLocalPopularPaginationPaged()


    private fun observeRemoteTopRatedPaginationPaged(scope: CoroutineScope): LiveData<PagedList<TvTopRatedPaginationData>> {
        val dataSourceFactory = TopRatedRemotePagingDataFactory(remoteSource, db.tvTopRatedPaginationDao(), scope)
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }

    private fun observeLocalTopRatedPaginationPaged(): LiveData<PagedList<TvTopRatedPaginationData>> {
        val dataSourceFactory = db.tvTopRatedPaginationDao().loadAllPagination()
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }
}