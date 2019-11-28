package com.ian.app.muviepedia.usecase

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
import com.ian.app.muviepedia.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MovieUseCase(private val movieRepository: MovieRepository) {

    fun getCacheNowPlayingMovie(): LiveData<ResultToConsume<List<MovieLocalNowPlayingData>>> =
        movieRepository.getCacheNowPlayingMovie()

    fun getCachePopularMovie(): LiveData<ResultToConsume<List<MovieLocalPopularData>>> =
        movieRepository.getCachePopularMovie()

    fun getCacheSearchMovie(querry: String): LiveData<ResultToConsume<List<MovieLocalSearchData>>> =
        movieRepository.getCacheSearchMovie(querry)

    fun getCacheUpComingMovie(): LiveData<ResultToConsume<List<MovieLocalUpComingData>>> =
        movieRepository.getCacheUpComingMovie()

    fun getCacheSingleDetailMovie(localSelectedId: Int): LiveData<MovieLocalSaveDetailData> =
        movieRepository.getCacheSingleDetailMovie(localSelectedId)

    fun getAllSingleDetailMovie(): LiveData<List<MovieLocalSaveDetailData>> =
        movieRepository.getAllSingleDetailMovie()

    fun getRemoteDetailMovie(movieId: Int): LiveData<ResultToConsume<MovieRemoteDetailData>> =
        movieRepository.getRemoteDetailMovie(movieId)

    fun getRemoteSimilarMovie(movieId: Int): LiveData<ResultToConsume<List<MovieRemoteData>>> =
        movieRepository.getRemoteSimilarMovie(movieId)

    fun getCachePaginationPopularMovie(): LiveData<PagedList<MovieLocalPopularPaginationData>> =
        movieRepository.getCachePaginationPopularMovie()

    fun getCachePaginationUpComingMovie(): LiveData<PagedList<MovieLocalUpComingPaginationData>> =
        movieRepository.getCachePaginationUpComingMovie()

    suspend fun setCacheDetailMovie(data: MovieLocalSaveDetailData) {
        movieRepository.setCacheDetailMovie(data)
    }

    suspend fun deleteAllDetailCache() {
        movieRepository.deleteAllDetailCache()
    }

    suspend fun deleteSelectedDetailCache(localId: Int) {
        movieRepository.deleteSelectedDetailCache(localId)
    }

    suspend fun clearSearchMovie() {
        movieRepository.clearSearchMovie()
    }
}