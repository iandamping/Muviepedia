package com.ian.app.muviepedia.data.cleanarch.datasource.cache

import androidx.paging.DataSource
import com.ian.app.muviepedia.data.MovieDatabase
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalNowPlayingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSaveDetailEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToDomain
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToSingleDomain
import com.ian.app.muviepedia.model.movie.MovieLocalNowPlayingData
import com.ian.app.muviepedia.model.movie.MovieLocalPopularData
import com.ian.app.muviepedia.model.movie.MovieLocalSaveDetailData
import com.ian.app.muviepedia.model.movie.MovieLocalSearchData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MovieCacheDataSourceImpl(private val db: MovieDatabase) : MovieCacheDataSource {
    override suspend fun setCachePopularMovie(data: List<MovieLocalPopularEntity>) {
        db.moviePopularDao().insertAll(data)
    }

    override suspend fun setCacheNowPlayingMovie(data: List<MovieLocalNowPlayingEntity>) {
        db.movieNowPlayingDao().insertAll(data)
    }

    override suspend fun setCacheUpComingMovie(data: List<MovieLocalUpComingEntity>) {
        db.movieUpComingDao().insertAll(data)
    }

    override suspend fun setCacheDetailMovie(data: MovieLocalSaveDetailEntity) {
        db.movieSaveDetailDao().insertAll(data)
    }

    override suspend fun setCacheSearchMovie(data: List<MovieLocalSearchEntity>) {
        db.movieSearchDao().updateData(data)
    }

    override suspend fun setCachePaginationPopularMovie(inputMovie: List<MovieLocalPopularPaginationEntity>) {
        db.moviePopularPaginationDao().insertAll(inputMovie)
    }

    override suspend fun setCachePaginationUpComingMovie(inputMovie: List<MovieLocalUpComingPaginationEntity>) {
        db.movieUpComingPaginationDao().insertAll(inputMovie)
    }

    override fun getCacheSingleDetailMovie(localSelectedId: Int): Flow<MovieLocalSaveDetailData> {
        return db.movieSaveDetailDao().loadAllMovieDataById(localSelectedId).mapToSingleDomain()
    }

    override fun getCacheAllSingleDetailMovie(): Flow<List<MovieLocalSaveDetailData>> {
        return db.movieSaveDetailDao().loadAll().mapToDomain()
    }

    override fun getCacheNowPlayingMovie(): Flow<List<MovieLocalNowPlayingData>> {
        return db.movieNowPlayingDao().loadAll().mapToDomain()
    }

    override fun getCachePopularMovie(): Flow<List<MovieLocalPopularData>> {
       return db.moviePopularDao().loadAll().mapToDomain()
    }

    override fun getCacheUpComingMovie(): Flow<List<MovieLocalUpComingData>> {
       return db.movieUpComingDao().loadAll().mapToDomain()
    }

    override fun getCacheSearchMovie(): Flow<List<MovieLocalSearchData>> {
        return db.movieSearchDao().loadAll().mapToDomain()
    }

    override fun getCachePaginationPopularMovie(): DataSource.Factory<Int, MovieLocalPopularPaginationEntity> {
        return db.moviePopularPaginationDao().loadAllPagination()
    }

    override fun getCachePaginationUpComingMovie(): DataSource.Factory<Int, MovieLocalUpComingPaginationEntity> {
       return db.movieUpComingPaginationDao().loadAllPagination()
    }

    override suspend fun deleteAllDetailCache() {
        db.movieSaveDetailDao().deleteAllData()
    }

    override suspend fun deleteSelectedDetailCache(localId: Int) {
        db.movieSaveDetailDao().deleteSelectedId(localId)
    }

    override suspend fun clearSearchMovie() {
        db.movieSearchDao().deleteAllData()
    }
}