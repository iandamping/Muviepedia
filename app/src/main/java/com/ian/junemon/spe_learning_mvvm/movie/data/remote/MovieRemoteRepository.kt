package com.ian.junemon.spe_learning_mvvm.movie.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.data.resultLiveData
import com.ian.junemon.spe_learning_mvvm.data.searchResultLiveData
import com.ian.junemon.spe_learning_mvvm.data.singleResultLiveData
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalDao
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalData
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteDataSourceFactory.Companion.pagedListConfig
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.nowPlayingMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.upcomingMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 *
Created by Ian Damping on 19/09/2019.
Github = https://github.com/iandamping
 */
class MovieRemoteRepository(private val remoteSource: MovieRemoteDataSource, private val dao: MovieLocalDao) {

    fun observeNowPlayingMovie() = resultLiveData(
            databaseQuery = { dao.loadSelectedCategory(nowPlayingMovie) },
            networkCall = { remoteSource.getNowPlayingMovie() },
            saveCallResult = { dao.insertAll(it.results.toNowPlayingMovie()) }
    ).distinctUntilChanged()


    fun observePopularMovie() = resultLiveData(
            databaseQuery = { dao.loadSelectedCategory(popularMovie) },
            networkCall = { remoteSource.getPopularMovie() },
            saveCallResult = { dao.insertAll(it.results.toPopularMovie()) }
    ).distinctUntilChanged()


    fun observeUpComingMovie() = resultLiveData(
            databaseQuery = { dao.loadSelectedCategory(upcomingMovie) },
            networkCall = { remoteSource.getUpComingMovie() },
            saveCallResult = { dao.insertAll(it.results.toUpComingMovie()) }
    ).distinctUntilChanged()

    fun getDetailMovie(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getDetailMovie(movieId) }
    )

    fun getSimilarMovie(movieId: Int) = singleResultLiveData(
            networkCall = { remoteSource.getSimilarMovie(movieId) }
    )

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun observeSearchMovie(querry: String) = searchResultLiveData(querry) {
        remoteSource.getSearchMovieResponse(querry)
    }.distinctUntilChanged()

    fun observePagination(connectivityAvailable: Boolean, movieType: String, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePaged(movieType, scope)
            else observeLocalPaged(movieType)


    private fun observeRemotePaged(movieType: String, scope: CoroutineScope): LiveData<PagedList<MovieLocalData>> {
        val dataSourceFactory = MovieRemoteDataSourceFactory(movieType, remoteSource, dao, scope)
        return LivePagedListBuilder(dataSourceFactory, pagedListConfig()).build()
    }

    private fun observeLocalPaged(movieType: String): LiveData<PagedList<MovieLocalData>> {
        val dataSourceFactory = dao.loadAllPagination(movieType)
        return LivePagedListBuilder(dataSourceFactory, pagedListConfig()).build()
    }
}