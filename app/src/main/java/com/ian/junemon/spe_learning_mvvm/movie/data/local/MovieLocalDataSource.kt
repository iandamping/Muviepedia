package com.ian.junemon.spe_learning_mvvm.movie.data.local

import com.ian.junemon.spe_learning_mvvm.data.MovieDatabase
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.*
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.toNowPlayingMovie

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
class MovieLocalDataSource(private val db:MovieDatabase) {

    val getDetailSavedMovieData by lazy { db.movieSaveDetailDao().loadAll() }

    val getNowPlayingMovieData by lazy { db.movieNowPlayingDao().loadAll() }

    val getPopularMovieData by lazy { db.moviePopularDao().loadAll() }

    val getUpComingMovieData by lazy { db.movieUpComingDao().loadAll() }

    val getSearchLocalData by lazy {db.movieSearchDao().loadAll()}

    val popularMoviePaginationDao by lazy { db.moviePopularPaginationDao() }

    val getPopularMoviePaginationData by lazy { db.moviePopularPaginationDao().loadAllPagination() }

    val upComingPaginationDao by lazy { db.movieUpComingPaginationDao() }

    val getUpComingPaginationData by lazy { db.movieUpComingPaginationDao().loadAllPagination() }

    suspend fun saveDetailMovieData(data: MovieSaveDetailData) = db.movieSaveDetailDao().insertAll(data)

    suspend fun saveNowPlayingMovie(data:List<MovieNowPlayingLocalData>) =  db.movieNowPlayingDao().insertAll(data)

    suspend fun savePopularMovie(data:List<MoviePopularLocalData>) =  db.moviePopularDao().insertAll(data)

    suspend fun saveUpComingMovie(data:List<MovieUpComingLocalData>) =  db.movieUpComingDao().insertAll(data)

    suspend fun updateSearchMovie(data:List<MovieSearchLocalData>) =  db.movieSearchDao().updateData(data)

    suspend fun deleteSelectedDetailMovieData(selectedId:Int) = db.movieSaveDetailDao().deleteSelectedId(selectedId)

    suspend fun clearSearchMovie() = db.movieSearchDao().deleteAllData()



}