package com.ian.app.muviepedia.data.cleanarch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.app.muviepedia.data.base.searchResultLiveData
import com.ian.app.muviepedia.data.base.singleResultLiveData
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.repository.paginationfactory.movie.MoviePaginationPopularFactory
import com.ian.app.muviepedia.data.cleanarch.data.repository.paginationfactory.movie.MoviePaginationUpComingFactory
import com.ian.app.muviepedia.data.cleanarch.datasource.cache.ssotResultLiveDatas
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToDatabase
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToDomain
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muviepedia.model.movie.MovieLocalNowPlayingData
import com.ian.app.muviepedia.model.movie.MovieLocalPopularData
import com.ian.app.muviepedia.model.movie.MovieLocalPopularPaginationData
import com.ian.app.muviepedia.model.movie.MovieLocalSaveDetailData
import com.ian.app.muviepedia.model.movie.MovieLocalSearchData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingPaginationData
import com.ian.app.muviepedia.model.movie.MovieRemoteData
import com.ian.app.muviepedia.model.movie.MovieRemoteDetailData
import com.ian.app.muviepedia.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MovieRepositoryImpl(
    private val remoteSource: MovieRemoteDataSource,
    private val localSource: MovieCacheDataSource
) : MovieRepository {

    override fun getCacheNowPlayingMovie(): LiveData<ResultToConsume<List<MovieLocalNowPlayingData>>> {
        return ssotResultLiveDatas(
            databaseQuery = { localSource.getCacheNowPlayingMovie() },
            networkCall = { remoteSource.getRemoteNowPlayingMovie() },
            saveCallResult = { localSource.setCacheNowPlayingMovie(it) }
        )
    }

    override fun getCachePopularMovie(): LiveData<ResultToConsume<List<MovieLocalPopularData>>> {
        return ssotResultLiveDatas(
            databaseQuery = { localSource.getCachePopularMovie() },
            networkCall = { remoteSource.getRemotePopularMovie() },
            saveCallResult = { localSource.setCachePopularMovie(it) }
        )
    }

    override fun getCacheSearchMovie(querry: String): LiveData<ResultToConsume<List<MovieLocalSearchData>>> {
        return searchResultLiveData(querry,
            databaseQuery = { localSource.getCacheSearchMovie().asLiveData() },
            networkCall = { remoteSource.getRemoteSearchMovie(querry) },
            saveCallResult = { localSource.setCacheSearchMovie(it) }
        )
    }

    override fun getCacheUpComingMovie(): LiveData<ResultToConsume<List<MovieLocalUpComingData>>> {
        return ssotResultLiveDatas(
            databaseQuery = { localSource.getCacheUpComingMovie() },
            networkCall = { remoteSource.getRemoteUpComingMovie() },
            saveCallResult = { localSource.setCacheUpComingMovie(it) }
        )
    }

    override fun getCacheSingleDetailMovie(localSelectedId: Int): LiveData<MovieLocalSaveDetailData> {
        return localSource.getCacheSingleDetailMovie(localSelectedId).asLiveData()
    }

    override fun getAllSingleDetailMovie(): LiveData<List<MovieLocalSaveDetailData>> {
        return localSource.getCacheAllSingleDetailMovie().asLiveData()
    }

    override fun getRemoteDetailMovie(movieId: Int): LiveData<ResultToConsume<MovieRemoteDetailData>> {
        return singleResultLiveData {
            remoteSource.getRemoteDetailMovie(
                movieId
            )
        }
    }

    override fun getRemoteSimilarMovie(movieId: Int): LiveData<ResultToConsume<List<MovieRemoteData>>> {
        return singleResultLiveData {
            remoteSource.getRemoteSimilarMovie(
                movieId
            )
        }
    }

    override fun getCachePaginationPopularMovie(scope: CoroutineScope): LiveData<PagedList<MovieLocalPopularPaginationData>> {
        val dataSourceFactory =
            MoviePaginationPopularFactory(remoteSource, localSource, scope).map { it.mapToDomain() }
        return LivePagedListBuilder(
            dataSourceFactory,
            MoviePaginationPopularFactory.pagedListConfig()
        ).build()
    }

    override fun getCachePaginationUpComingMovie(scope: CoroutineScope): LiveData<PagedList<MovieLocalUpComingPaginationData>> {
        val dataSourceFactory = MoviePaginationUpComingFactory(
            remoteSource,
            localSource,
            scope
        ).map { it.mapToDomain() }
        return LivePagedListBuilder(
            dataSourceFactory,
            MoviePaginationUpComingFactory.pagedListConfig()
        ).build()
    }

    override suspend fun setCacheDetailMovie(data: MovieLocalSaveDetailData) {
        localSource.setCacheDetailMovie(data.mapToDatabase())
    }

    override suspend fun deleteAllDetailCache() {
        localSource.deleteAllDetailCache()
    }

    override suspend fun deleteSelectedDetailCache(localId: Int) {
        localSource.deleteSelectedDetailCache(localId)
    }

    override suspend fun clearSearchMovie() {
        localSource.clearSearchMovie()
    }
}