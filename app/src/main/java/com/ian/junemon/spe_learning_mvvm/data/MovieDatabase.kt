package com.ian.junemon.spe_learning_mvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.junemon.spe_learning_mvvm.movie.data.local.dao.*
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.*
import com.ian.junemon.spe_learning_mvvm.tv.data.local.dao.*
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.*

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
@Database(entities = [MovieNowPlayingLocalData::class,
    MoviePopularLocalData::class,
    MoviePopularPaginationData::class,
    MovieUpComingLocalData::class,
    MovieUpComingPaginationData::class,
    MovieSearchLocalData::class,
    TvAiringTodayData::class,
    TvPopularData::class,
    TvPopularPaginationData::class,
    TvTopRatedData::class,
    TvTopRatedPaginationData::class,
    TvSearchLocalData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieNowPlayingDao
    abstract fun moviePopularDao(): MoviePopularDao
    abstract fun movieUpComingDao(): MovieUpComingDao
    abstract fun movieUpComingPaginationDao(): MovieUpComingPaginationDao
    abstract fun moviePopularPaginationDao(): MoviePopularPaginationDao
    abstract fun movieSearchDao(): MovieSearchDao

    abstract fun tvAiringTodayDao(): TvAiringTodayDao
    abstract fun tvPopularDao(): TvPopularDao
    abstract fun tvTopRatedDao(): TvTopRatedDao
    abstract fun tvTopRatedPaginationDao(): TvTopRatedPaginationDao
    abstract fun tvPopularPaginationDao(): TvPopularPaginationDao
    abstract fun tvSearchDao(): TvSearchDao

}



