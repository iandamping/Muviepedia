package com.ian.app.muviepedia.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ian.app.muviepedia.model.movie.MovieLocalNowPlayingData
import com.ian.app.muviepedia.model.movie.MovieLocalPopularData
import com.ian.app.muviepedia.model.movie.MovieLocalPopularPaginationData
import com.ian.app.muviepedia.model.movie.MovieLocalSaveDetailData
import com.ian.app.muviepedia.model.movie.MovieLocalSearchData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingPaginationData
import com.ian.app.muviepedia.model.movie.MovieRemoteData
import com.ian.app.muviepedia.model.movie.MovieRemoteDetailData
import com.ian.app.muviepedia.model.ResultToConsume
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface MovieRepository {
    fun getCacheNowPlayingMovie(): LiveData<ResultToConsume<List<MovieLocalNowPlayingData>>>
    fun getCachePopularMovie(): LiveData<ResultToConsume<List<MovieLocalPopularData>>>
    fun getCacheSearchMovie(querry: String): LiveData<ResultToConsume<List<MovieLocalSearchData>>>
    fun getCacheUpComingMovie(): LiveData<ResultToConsume<List<MovieLocalUpComingData>>>
    fun getCacheSingleDetailMovie(localSelectedId: Int): LiveData<MovieLocalSaveDetailData>
    fun getAllSingleDetailMovie(): LiveData<List<MovieLocalSaveDetailData>>
    fun getRemoteDetailMovie(movieId: Int): LiveData<ResultToConsume<MovieRemoteDetailData>>
    fun getRemoteSimilarMovie(movieId: Int): LiveData<ResultToConsume<List<MovieRemoteData>>>
    fun getCachePaginationPopularMovie(): LiveData<PagedList<MovieLocalPopularPaginationData>>
    fun getCachePaginationUpComingMovie(): LiveData<PagedList<MovieLocalUpComingPaginationData>>
    suspend fun setCacheDetailMovie(data: MovieLocalSaveDetailData)
    suspend fun deleteAllDetailCache()
    suspend fun deleteSelectedDetailCache(localId: Int)
    suspend fun clearSearchMovie()
}