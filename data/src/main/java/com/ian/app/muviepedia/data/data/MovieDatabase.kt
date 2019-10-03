package com.ian.app.muviepedia.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.app.muviepedia.data.data_source.movie.data.local.dao.*
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.*
import com.ian.app.muviepedia.data.data_source.tv.data.local.dao.*
import com.ian.app.muviepedia.data.data_source.tv.data.local.model.*
import com.ian.app.muviepedia.tv.data.local.dao.TvAiringTodayDao
import com.ian.app.muviepedia.data.data_source.tv.data.local.dao.TvSaveDetailDao
import com.ian.app.muviepedia.tv.data.local.model.TvAiringTodayData

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
    MovieSaveDetailData::class,
    TvAiringTodayData::class,
    TvPopularData::class,
    TvPopularPaginationData::class,
    TvTopRatedData::class,
    TvTopRatedPaginationData::class,
    TvSearchLocalData::class,
    TvSaveDetailData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieNowPlayingDao
    abstract fun moviePopularDao(): MoviePopularDao
    abstract fun movieUpComingDao(): MovieUpComingDao
    abstract fun movieUpComingPaginationDao(): MovieUpComingPaginationDao
    abstract fun moviePopularPaginationDao(): MoviePopularPaginationDao
    abstract fun movieSearchDao(): MovieSearchDao
    abstract fun movieSaveDetailDao(): MovieSaveDetailDao

    abstract fun tvAiringTodayDao(): TvAiringTodayDao
    abstract fun tvPopularDao(): TvPopularDao
    abstract fun tvTopRatedDao(): TvTopRatedDao
    abstract fun tvTopRatedPaginationDao(): TvTopRatedPaginationDao
    abstract fun tvPopularPaginationDao(): TvPopularPaginationDao
    abstract fun tvSearchDao(): TvSearchDao
    abstract fun tvSaveDetailDao(): TvSaveDetailDao

}

