package com.ian.junemon.spe_learning_mvvm.movie.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.data.MovieDatabase
import com.ian.junemon.spe_learning_mvvm.data.resultLiveData
import com.ian.junemon.spe_learning_mvvm.data.searchResultLiveData
import com.ian.junemon.spe_learning_mvvm.data.singleResultLiveData
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MoviePopularPaginationData
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.MovieUpComingPaginationData
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.pagination.popular.MoviePopularRemoteDataSourceFactory
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.pagination.upcoming.MovieUpComingRemoteDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 *
Created by Ian Damping on 19/09/2019.
Github = https://github.com/iandamping
 */
class MovieRemoteRepository(private val remoteSource: MovieRemoteDataSource, private val db: MovieDatabase) {

    fun observeNowPlayingMovie(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { db.movieNowPlayingDao().loadAll() },
            networkCall = { remoteSource.getNowPlayingMovie() },
            saveCallResult = { db.movieNowPlayingDao().insertAll(it.results.toNowPlayingMovie(scope)) }
    ).distinctUntilChanged()


    fun observePopularMovie(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { db.moviePopularDao().loadAll() },
            networkCall = { remoteSource.getPopularMovie() },
            saveCallResult = { db.moviePopularDao().insertAll(it.results.toPopularMovie(scope)) }
    ).distinctUntilChanged()


    fun observeUpComingMovie(scope: CoroutineScope) = resultLiveData(
            databaseQuery = { db.movieUpComingDao().loadAll() },
            networkCall = { remoteSource.getUpComingMovie() },
            saveCallResult = { db.movieUpComingDao().insertAll(it.results.toUpComingMovie(scope)) }
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
        remoteSource.getSearchMovie(querry)
    }.distinctUntilChanged()


    fun observePopularPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePopularPaginationPaged(scope)
            else observeLocalPopularPaginationPaged()


    private fun observeRemotePopularPaginationPaged(scope: CoroutineScope): LiveData<PagedList<MoviePopularPaginationData>> {
        val dataSourceFactory = MoviePopularRemoteDataSourceFactory(remoteSource, db.moviePopularPaginationDao(), scope)
        return LivePagedListBuilder(dataSourceFactory, MoviePopularRemoteDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeLocalPopularPaginationPaged(): LiveData<PagedList<MoviePopularPaginationData>> {
        val dataSourceFactory = db.moviePopularPaginationDao().loadAllPagination()
        return LivePagedListBuilder(dataSourceFactory, MoviePopularRemoteDataSourceFactory.pagedListConfig()).build()
    }


    fun observeUpComingPagination(connectivityAvailable: Boolean, scope: CoroutineScope) =
            if (connectivityAvailable) observeRemoteUpComingPaginationPaged(scope)
            else observeLocalUpComingPaginationPaged()


    private fun observeRemoteUpComingPaginationPaged(scope: CoroutineScope): LiveData<PagedList<MovieUpComingPaginationData>> {
        val dataSourceFactory = MovieUpComingRemoteDataSourceFactory(remoteSource, db.movieUpComingPaginationDao(), scope)
        return LivePagedListBuilder(dataSourceFactory, MovieUpComingRemoteDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeLocalUpComingPaginationPaged(): LiveData<PagedList<MovieUpComingPaginationData>> {
        val dataSourceFactory = db.movieUpComingPaginationDao().loadAllPagination()
        return LivePagedListBuilder(dataSourceFactory, MovieUpComingRemoteDataSourceFactory.pagedListConfig()).build()
    }
}