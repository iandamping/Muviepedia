package com.ian.junemon.spe_learning_mvvm.di

import androidx.room.Room
import com.ian.junemon.spe_learning_mvvm.data.MovieDatabase
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
val databaseModule = module {
    // Room Database instance
    single {
        Room.databaseBuilder(get(), MovieDatabase::class.java, "LocalMovieDatabase").fallbackToDestructiveMigration()
                .build()
    }
    // localDao instance (get instance from MovieDatabase)
    single { get<MovieDatabase>().movieNowPlayingDao() }
    single { get<MovieDatabase>().moviePopularDao() }
    single { get<MovieDatabase>().movieUpComingDao() }
    single { get<MovieDatabase>().movieUpComingPaginationDao() }
    single { get<MovieDatabase>().moviePopularPaginationDao() }
    single { get<MovieDatabase>().movieSearchDao() }

    single { get<MovieDatabase>().tvAiringTodayDao() }
    single { get<MovieDatabase>().tvPopularDao() }
    single { get<MovieDatabase>().tvPopularPaginationDao() }
    single { get<MovieDatabase>().tvTopRatedDao() }
    single { get<MovieDatabase>().tvTopRatedPaginationDao() }
    single { get<MovieDatabase>().tvSearchDao() }

}