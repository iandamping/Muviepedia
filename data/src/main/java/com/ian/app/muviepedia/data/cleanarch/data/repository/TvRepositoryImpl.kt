package com.ian.app.muviepedia.data.cleanarch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.repository.paginationfactory.tvshow.TvPaginationPopularFactory
import com.ian.app.muviepedia.data.cleanarch.data.repository.paginationfactory.tvshow.TvPaginationTopRatedFactory
import com.ian.app.muviepedia.data.cleanarch.datasource.cache.ssotResultLiveDatas
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToDatabase
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToDomain
import com.ian.app.muviepedia.data.base.searchResultLiveData
import com.ian.app.muviepedia.data.base.singleResultLiveData
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muviepedia.model.tvshow.TvLocalAiringTodayData
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularData
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularPaginationData
import com.ian.app.muviepedia.model.tvshow.TvLocalSaveDetailData
import com.ian.app.muviepedia.model.tvshow.TvLocalSearchData
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedData
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedPaginationData
import com.ian.app.muviepedia.model.tvshow.TvRemoteData
import com.ian.app.muviepedia.model.tvshow.TvRemoteDetailData
import com.ian.app.muviepedia.repository.TvRepository
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvRepositoryImpl(
    private val remoteSource: TvRemoteDataSource,
    private val localSource: TvCacheDataSource
) : TvRepository {
    override fun getCacheAiringTodayTv(): LiveData<ResultToConsume<List<TvLocalAiringTodayData>>> {
        return ssotResultLiveDatas(
            databaseQuery = { localSource.getCacheAiringTodayTv() },
            networkCall = { remoteSource.getRemoteAiringTodayTv() },
            saveCallResult = { localSource.setCacheAiringTodayTv(it) }
        )
    }

    override fun getCachePopularTv(): LiveData<ResultToConsume<List<TvLocalPopularData>>> {
        return ssotResultLiveDatas(
            databaseQuery = { localSource.getCachePopularTv() },
            networkCall = { remoteSource.getRemotePopularTv() },
            saveCallResult = { localSource.setCachePopularTv(it) }
        )
    }

    override fun getCacheTopRatedTv(): LiveData<ResultToConsume<List<TvLocalTopRatedData>>> {
        return ssotResultLiveDatas(
            databaseQuery = { localSource.getCacheTopRatedTv() },
            networkCall = { remoteSource.getRemoteTopRatedTv() },
            saveCallResult = { localSource.setCacheTopRatedTv(it) }
        )
    }

    override fun getCacheSingleDetailTv(localSelectedId: Int): LiveData<TvLocalSaveDetailData> {
        return localSource.getCacheSingleDetailMovie(localSelectedId).asLiveData()
    }

    override fun getAllSingleDetailTv(): LiveData<List<TvLocalSaveDetailData>> {
        return localSource.getCacheAllSingleDetailMovie().asLiveData()
    }

    override fun getCacheSearchTv(querry: String): LiveData<ResultToConsume<List<TvLocalSearchData>>> {
        return searchResultLiveData(querry,
            databaseQuery = { localSource.getCacheSearchTv().asLiveData() },
            networkCall = { remoteSource.getRemoteSearchTv(querry) },
            saveCallResult = { localSource.setCacheSearchTv(it) }
        )
    }

    override fun getRemoteDetailTv(movieId: Int): LiveData<ResultToConsume<TvRemoteDetailData>> {
        return singleResultLiveData(networkCall = {
            remoteSource.getRemoteDetailTv(movieId)
        })
    }

    override fun getRemoteSimilarTv(movieId: Int): LiveData<ResultToConsume<List<TvRemoteData>>> {
        return singleResultLiveData(networkCall = {
            remoteSource.getRemoteSimilarTV(
                movieId
            )
        })
    }

    override fun getCachePaginationPopularTv(): LiveData<PagedList<TvLocalPopularPaginationData>> {
        val dataSourceFactory = TvPaginationPopularFactory(remoteSource, localSource).map { it.mapToDomain() }
        return LivePagedListBuilder(dataSourceFactory, TvPaginationPopularFactory.pagedListConfig()).build()
    }

    override fun getCachePaginationTopRatedTv(): LiveData<PagedList<TvLocalTopRatedPaginationData>> {
        val dataSourceFactory = TvPaginationTopRatedFactory(remoteSource, localSource).map { it.mapToDomain() }
        return LivePagedListBuilder(dataSourceFactory, TvPaginationTopRatedFactory.pagedListConfig()).build()
    }

    override suspend fun setCacheDetailTv(data: TvLocalSaveDetailData) {
       localSource.setCacheDetailTv(data.mapToDatabase())
    }

    override suspend fun deleteAllDetailCache() {
       localSource.deleteAllDetailCache()
    }

    override suspend fun deleteSelectedDetailCache(localId: Int) {
        localSource.deleteSelectedDetailCache(localId)
    }

    override suspend fun clearSearchTv() {
        localSource.clearSearchTv()
    }
}