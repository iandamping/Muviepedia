package com.ian.app.muviepedia.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
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
class TvShowUseCase(private val repository: TvRepository) {
    fun getCacheAiringTodayTv(): LiveData<ResultToConsume<List<TvLocalAiringTodayData>>> =
        repository.getCacheAiringTodayTv()

    fun getCachePopularTv(): LiveData<ResultToConsume<List<TvLocalPopularData>>> =
        repository.getCachePopularTv()

    fun getCacheTopRatedTv(): LiveData<ResultToConsume<List<TvLocalTopRatedData>>> =
        repository.getCacheTopRatedTv()

    fun getCacheSingleDetailTv(localSelectedId: Int): LiveData<TvLocalSaveDetailData> =
        repository.getCacheSingleDetailTv(localSelectedId)

    fun getAllSingleDetailTv(): LiveData<List<TvLocalSaveDetailData>> =
        repository.getAllSingleDetailTv()

    fun getCacheSearchTv(querry: String): LiveData<ResultToConsume<List<TvLocalSearchData>>> =
        repository.getCacheSearchTv(querry)

    fun getRemoteDetailTv(movieId: Int): LiveData<ResultToConsume<TvRemoteDetailData>> =
        repository.getRemoteDetailTv(movieId)

    fun getRemoteSimilarTv(movieId: Int): LiveData<ResultToConsume<List<TvRemoteData>>> =
        repository.getRemoteSimilarTv(movieId)

    fun getCachePaginationPopularTv(scope: CoroutineScope): LiveData<PagedList<TvLocalPopularPaginationData>> =
        repository.getCachePaginationPopularTv(scope)

    fun getCachePaginationTopRatedTv(scope: CoroutineScope): LiveData<PagedList<TvLocalTopRatedPaginationData>> =
        repository.getCachePaginationTopRatedTv(scope)

    suspend fun setCacheDetailTv(data: TvLocalSaveDetailData) = repository.setCacheDetailTv(data)
    suspend fun deleteAllDetailCache() = repository.deleteAllDetailCache()
    suspend fun deleteSelectedDetailCache(localId: Int) =
        repository.deleteSelectedDetailCache(localId)

    suspend fun clearSearchTv() = repository.clearSearchTv()
}