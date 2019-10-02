package com.ian.app.muviepedia.data.data_source.movie.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.data.resultLiveData
import com.ian.app.muviepedia.data.data.searchResultLiveData
import com.ian.app.muviepedia.data.data.singleResultLiveData
import com.ian.app.muviepedia.data.data_source.movie.data.local.MovieLocalDataSource
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MoviePopularPaginationData
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MovieSaveDetailData
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.MovieUpComingPaginationData
import com.ian.app.muviepedia.data.data_source.movie.data.remote.pagination.popular.MoviePopularRemoteDataSourceFactory
import com.ian.app.muviepedia.data.data_source.movie.data.remote.pagination.upcoming.MovieUpComingRemoteDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 *
Created by Ian Damping on 19/09/2019.
Github = https://github.com/iandamping
 */
class MovieRemoteRepository(private val remoteSource: MovieRemoteDataSource, private val localSource: MovieLocalDataSource) {

    val getDetailData by lazy { localSource.getDetailSavedMovieData }

    suspend fun saveDetailData(data: MovieSaveDetailData) = localSource.saveDetailMovieData(data)

    suspend fun deleteSelectedDetailData(selectedId: Int) = localSource.deleteSelectedDetailMovieData(selectedId)

    fun observeNowPlayingMovie(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { localSource.getNowPlayingMovieData },
            networkCall = { remoteSource.getNowPlayingMovie() },
            saveCallResult = { localSource.saveNowPlayingMovie(it.results.toNowPlayingMovie(scope)) }
    ).distinctUntilChanged()


    fun observePopularMovie(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { localSource.getPopularMovieData },
            networkCall = { remoteSource.getPopularMovie() },
            saveCallResult = { localSource.savePopularMovie(it.results.toPopularMovie(scope)) }
    ).distinctUntilChanged()


    fun observeUpComingMovie(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { localSource.getUpComingMovieData },
            networkCall = { remoteSource.getUpComingMovie() },
            saveCallResult = { localSource.saveUpComingMovie(it.results.toUpComingMovie(scope)) }
    ).distinctUntilChanged()

    fun getDetailMovie(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getDetailMovie(movieId) }
    )

    fun getSimilarMovie(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getSimilarMovie(movieId) }
    )

    suspend fun clearSearchMovie() = localSource.clearSearchMovie()

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun observeSearchMovie(querry: String, scope: CoroutineScope) = searchResultLiveData(querry,
            databaseQuery = { localSource.getSearchLocalData },
            networkCall = { remoteSource.getSearchMovie(querry) },
            saveCallResult = { localSource.updateSearchMovie(it.results.toSearchMovie(scope)) }
    ).distinctUntilChanged()


    fun observePopularPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePopularPaginationPaged(scope)
            else observeLocalPopularPaginationPaged()


    private fun observeRemotePopularPaginationPaged(scope: CoroutineScope): LiveData<PagedList<MoviePopularPaginationData>> {
        val dataSourceFactory = MoviePopularRemoteDataSourceFactory(remoteSource, localSource.popularMoviePaginationDao, scope)
        return LivePagedListBuilder(dataSourceFactory, MoviePopularRemoteDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeLocalPopularPaginationPaged(): LiveData<PagedList<MoviePopularPaginationData>> {
        val dataSourceFactory = localSource.getPopularMoviePaginationData
        return LivePagedListBuilder(dataSourceFactory, MoviePopularRemoteDataSourceFactory.pagedListConfig()).build()
    }


    fun observeUpComingPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemoteUpComingPaginationPaged(scope)
            else observeLocalUpComingPaginationPaged()


    private fun observeRemoteUpComingPaginationPaged(scope: CoroutineScope): LiveData<PagedList<MovieUpComingPaginationData>> {
        val dataSourceFactory = MovieUpComingRemoteDataSourceFactory(remoteSource, localSource.upComingPaginationDao, scope)
        return LivePagedListBuilder(dataSourceFactory, MovieUpComingRemoteDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeLocalUpComingPaginationPaged(): LiveData<PagedList<MovieUpComingPaginationData>> {
        val dataSourceFactory = localSource.getUpComingPaginationData
        return LivePagedListBuilder(dataSourceFactory, MovieUpComingRemoteDataSourceFactory.pagedListConfig()).build()
    }
}