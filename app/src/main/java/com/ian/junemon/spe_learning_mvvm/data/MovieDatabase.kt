package com.ian.junemon.spe_learning_mvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ian.junemon.spe_learning_mvvm.movie.data.local.dao.*
import com.ian.junemon.spe_learning_mvvm.movie.data.local.model.*
import com.ian.junemon.spe_learning_mvvm.tv.data.local.dao.*
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.*
import kotlin.reflect.KClass

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
    TvAiringTodayData::class,
    TvPopularData::class,
    TvPopularPaginationData::class,
    TvTopRatedData::class,
    TvTopRatedPaginationData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieNowPlayingDao
    abstract fun moviePopularDao(): MoviePopularDao
    abstract fun movieUpComingDao(): MovieUpComingDao
    abstract fun movieUpComingPaginationDao(): MovieUpComingPaginationDao
    abstract fun moviePopularPaginationDao(): MoviePopularPaginationDao

    abstract fun tvAiringTodayDao(): TvAiringTodayDao
    abstract fun tvPopularDao(): TvPopularDao
    abstract fun tvTopRatedDao(): TvTopRatedDao
    abstract fun tvTopRatedPaginationDao(): TvTopRatedPaginationDao
    abstract fun tvPopularPaginationDao(): TvPopularPaginationDao
}

/*private fun initDatabaseClass(): Array<KClass<*>> =
        arrayOf(
                MovieNowPlayingLocalData::class,
                MoviePopularLocalData::class,
                MoviePopularPaginationData::class,
                MovieUpComingLocalData::class,
                MovieUpComingPaginationData::class,
                TvAiringTodayData::class,
                TvPopularData::class,
                TvPopularPaginationData::class,
                TvTopRatedData::class,
                TvTopRatedPaginationData::class)*/
/*val arrayKClass:MutableList<KClass<*>> = mutableListOf()
 arrayKClass.add(MovieNowPlayingLocalData::class)
 arrayKClass.add(MoviePopularLocalData::class)
 arrayKClass.add(MoviePopularPaginationData::class)
 arrayKClass.add(MovieUpComingLocalData::class)
 arrayKClass.add(MovieUpComingPaginationData::class)
 arrayKClass.add(TvAiringTodayData::class)
 arrayKClass.add(TvPopularData::class)
 arrayKClass.add(TvPopularPaginationData::class)
 arrayKClass.add(TvTopRatedData::class)
 arrayKClass.add(TvTopRatedPaginationData::class)
 return arrayK*/


