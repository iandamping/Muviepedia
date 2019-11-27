package com.ian.app.muviepedia.repository

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
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface TvRepository {
    fun getCacheAiringTodayTv(): LiveData<ResultToConsume<List<TvLocalAiringTodayData>>>
    fun getCachePopularTv(): LiveData<ResultToConsume<List<TvLocalPopularData>>>
    fun getCacheTopRatedTv(): LiveData<ResultToConsume<List<TvLocalTopRatedData>>>
    fun getCacheSingleDetailTv(localSelectedId: Int): LiveData<TvLocalSaveDetailData>
    fun getAllSingleDetailTv(): LiveData<List<TvLocalSaveDetailData>>
    fun getCacheSearchTv(querry: String): LiveData<ResultToConsume<List<TvLocalSearchData>>>
    fun getRemoteDetailTv(movieId: Int): LiveData<ResultToConsume<TvRemoteDetailData>>
    fun getRemoteSimilarTv(movieId: Int): LiveData<ResultToConsume<List<TvRemoteData>>>
    fun getCachePaginationPopularTv(scope: CoroutineScope): LiveData<PagedList<TvLocalPopularPaginationData>>
    fun getCachePaginationTopRatedTv(scope: CoroutineScope): LiveData<PagedList<TvLocalTopRatedPaginationData>>
    suspend fun setCacheDetailTv(data: TvLocalSaveDetailData)
    suspend fun deleteAllDetailCache()
    suspend fun deleteSelectedDetailCache(localId: Int)
    suspend fun clearSearchTv()
}