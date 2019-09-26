package com.ian.junemon.spe_learning_mvvm.tv.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.data.resultLiveData
import com.ian.junemon.spe_learning_mvvm.data.searchResultLiveData
import com.ian.junemon.spe_learning_mvvm.data.singleResultLiveData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.TvLocalDataSource
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvPopularPaginationData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvSaveDetailData
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
class TvRemoteRepository(private val remoteSource: TvRemoteDataSource, private val localSource: TvLocalDataSource) {

    val getDetailData by lazy { localSource.getDetailSavedTvData }

    suspend fun saveDetailData(data: TvSaveDetailData) = localSource.saveDetailTvData(data)

    suspend fun deleteSelectedDetailData(selectedId:Int) = localSource.deleteSelectedDetailTvData(selectedId)

    fun observeAiringToday(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { localSource.getAiringTodayTvData },
            networkCall = { remoteSource.getAiringTodayTv() },
            saveCallResult = { localSource.saveAiringTodayTv(it.results.toAiringToday(scope)) }
    ).distinctUntilChanged()

    fun observePopular(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { localSource.getPopularTvData },
            networkCall = { remoteSource.getPopularTv() },
            saveCallResult = { localSource.savePopularTv(it.results.toPopularTv(scope)) }
    ).distinctUntilChanged()

    fun observeTopRated(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { localSource.getTopRatedTvData },
            networkCall = { remoteSource.getTopRatedTv() },
            saveCallResult = { localSource.saveTopRatedTv(it.results.toTopRatedTv(scope)) }
    ).distinctUntilChanged()

    fun getDetailTv(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getDetailTv(movieId) }
    )

    fun getSimilarTv(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getSimilarTv(movieId) }
    )

    suspend fun clearSearchTvData() = localSource.clearSearchTv()

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun observeSearchTv(querry: String, scope: CoroutineScope) = searchResultLiveData(querry,
            databaseQuery = { localSource.getSearchTvData },
            networkCall = { remoteSource.getSearchTv(querry) },
            saveCallResult = { localSource.updateSearchTv(it.results.toSearchTv(scope)) })
            .distinctUntilChanged()


    fun observePopularTvPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePopularPaginationPaged(scope)
            else observeLocalPopularPaginationPaged()


    private fun observeRemotePopularPaginationPaged(scope: CoroutineScope): LiveData<PagedList<TvPopularPaginationData>> {
        val dataSourceFactory = PopularRemoteDataFactory(remoteSource, localSource.popularTvPaginationDao, scope)
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }

    private fun observeLocalPopularPaginationPaged(): LiveData<PagedList<TvPopularPaginationData>> {
        val dataSourceFactory = localSource.getPopularTvPaginationData
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }


    fun observeTopRatedPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemoteTopRatedPaginationPaged(scope)
            else observeLocalTopRatedPaginationPaged()


    private fun observeRemoteTopRatedPaginationPaged(scope: CoroutineScope): LiveData<PagedList<TvTopRatedPaginationData>> {
        val dataSourceFactory = TopRatedRemotePagingDataFactory(remoteSource, localSource.topRatedTvPaginationDao, scope)
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }

    private fun observeLocalTopRatedPaginationPaged(): LiveData<PagedList<TvTopRatedPaginationData>> {
        val dataSourceFactory = localSource.getTopRatedTvPaginationData
        return LivePagedListBuilder(dataSourceFactory, PopularRemoteDataFactory.pagedListConfig()).build()
    }
}