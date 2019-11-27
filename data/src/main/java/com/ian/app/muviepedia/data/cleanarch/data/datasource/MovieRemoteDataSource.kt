package com.ian.app.muviepedia.data.cleanarch.data.datasource

import androidx.paging.DataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalNowPlayingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSaveDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingPaginationEntity
import com.ian.app.muviepedia.model.movie.MovieLocalNowPlayingData
import com.ian.app.muviepedia.model.movie.MovieLocalPopularData
import com.ian.app.muviepedia.model.movie.MovieLocalSaveDetailData
import com.ian.app.muviepedia.model.movie.MovieLocalSearchData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingData
import com.ian.app.muviepedia.model.movie.MovieRemoteData
import com.ian.app.muviepedia.model.movie.MovieRemoteDetailData
import com.ian.app.muviepedia.model.ResultToConsume
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 25,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface MovieRemoteDataSource {

    suspend fun getRemotePopularMovie(): ResultToConsume<List<MovieLocalPopularEntity>>

    suspend fun getRemoteNowPlayingMovie(): ResultToConsume<List<MovieLocalNowPlayingEntity>>

    suspend fun getRemoteUpComingMovie(): ResultToConsume<List<MovieLocalUpComingEntity>>

    suspend fun getRemoteDetailMovie(movieId: Int): ResultToConsume<MovieRemoteDetailData>

    suspend fun getRemoteSimilarMovie(movieId: Int): ResultToConsume<List<MovieRemoteData>>

    suspend fun getRemoteSearchMovie(querry: String): ResultToConsume<List<MovieLocalSearchEntity>>

    suspend fun getRemotePaginationPopularMovie(page: Int): ResultToConsume<List<MovieLocalPopularPaginationEntity>>

    suspend fun getRemotePaginationUpComingMovie(page: Int): ResultToConsume<List<MovieLocalUpComingPaginationEntity>>
}

interface MovieCacheDataSource {

    suspend fun setCachePopularMovie(data: List<MovieLocalPopularEntity>)

    suspend fun setCacheNowPlayingMovie(data: List<MovieLocalNowPlayingEntity>)

    suspend fun setCacheUpComingMovie(data: List<MovieLocalUpComingEntity>)

    suspend fun setCacheDetailMovie(data: MovieLocalSaveDetailEntity)

    suspend fun setCacheSearchMovie(data: List<MovieLocalSearchEntity>)

    suspend fun setCachePaginationPopularMovie(inputMovie: List<MovieLocalPopularPaginationEntity>)

    suspend fun setCachePaginationUpComingMovie(inputMovie: List<MovieLocalUpComingPaginationEntity>)

    fun getCacheSingleDetailMovie(localSelectedId: Int): Flow<MovieLocalSaveDetailData>

    fun getCacheAllSingleDetailMovie(): Flow<List<MovieLocalSaveDetailData>>

    fun getCacheNowPlayingMovie(): Flow<List<MovieLocalNowPlayingData>>

    fun getCachePopularMovie(): Flow<List<MovieLocalPopularData>>

    fun getCacheUpComingMovie(): Flow<List<MovieLocalUpComingData>>

    fun getCacheSearchMovie(): Flow<List<MovieLocalSearchData>>

    fun getCachePaginationPopularMovie(): DataSource.Factory<Int, MovieLocalPopularPaginationEntity>

    fun getCachePaginationUpComingMovie(): DataSource.Factory<Int, MovieLocalUpComingPaginationEntity>

    suspend fun deleteAllDetailCache()

    suspend fun deleteSelectedDetailCache(localId: Int)

    suspend fun clearSearchMovie()
}