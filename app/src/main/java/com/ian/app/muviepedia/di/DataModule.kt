package com.ian.app.muviepedia.di

import androidx.room.Room
import com.ian.app.muviepedia.data.MovieDatabase
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.UserProfileCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.UserProfileRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.data.repository.MovieRepositoryImpl
import com.ian.app.muviepedia.data.cleanarch.data.repository.TvRepositoryImpl
import com.ian.app.muviepedia.data.cleanarch.data.repository.UserRepositoryImpl
import com.ian.app.muviepedia.data.cleanarch.datasource.cache.MovieCacheDataSourceImpl
import com.ian.app.muviepedia.data.cleanarch.datasource.cache.TvCacheDataSourceImpl
import com.ian.app.muviepedia.data.cleanarch.datasource.cache.UserCacheDataSourceImpl
import com.ian.app.muviepedia.data.cleanarch.datasource.remote.MovieRemoteDataSourceImpl
import com.ian.app.muviepedia.data.cleanarch.datasource.remote.TvRemoteDataSourceImpl
import com.ian.app.muviepedia.data.cleanarch.datasource.remote.UserRemoteDataSourceImpl
import com.ian.app.muviepedia.data.di.networkModule
import com.ian.app.muviepedia.data.util.PreferenceHelper
import com.ian.app.muviepedia.movie.di.movieModule
import com.ian.app.muviepedia.profile.di.profileModule
import com.ian.app.muviepedia.repository.MovieRepository
import com.ian.app.muviepedia.repository.TvRepository
import com.ian.app.muviepedia.repository.UserRepository
import com.ian.app.muviepedia.tvshow.di.tvModule
import com.ian.app.muviepedia.usecase.MovieUseCase
import com.ian.app.muviepedia.usecase.TvShowUseCase
import com.ian.app.muviepedia.usecase.UserUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */

fun injectData() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            databaseModule,
            networkModule,
            repositoryModule,
            dataSourceModule,
            tvModule,
            movieModule,
            profileModule,
            usecaseModule,
            preferenceHelperModule
        )
    )
}
/*private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            databaseModule,
            networkModule,
            repositoryModule,
            dataSourceModule,
            viewModelModule,
            preferenceHelperModule,
            fabricModule
        )
    )
}*/

val databaseModule = module {
    // Room Database instance
    single {
        Room.databaseBuilder(get(), MovieDatabase::class.java, "LocalMovieDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
    // localDao instance (get instance from MovieDatabase)
    single { get<MovieDatabase>().movieNowPlayingDao() }
    single { get<MovieDatabase>().moviePopularDao() }
    single { get<MovieDatabase>().movieUpComingDao() }
    single { get<MovieDatabase>().movieUpComingPaginationDao() }
    single { get<MovieDatabase>().moviePopularPaginationDao() }
    single { get<MovieDatabase>().movieSearchDao() }
    single { get<MovieDatabase>().movieSaveDetailDao() }

    single { get<MovieDatabase>().tvAiringTodayDao() }
    single { get<MovieDatabase>().tvPopularDao() }
    single { get<MovieDatabase>().tvPopularPaginationDao() }
    single { get<MovieDatabase>().tvTopRatedDao() }
    single { get<MovieDatabase>().tvTopRatedPaginationDao() }
    single { get<MovieDatabase>().tvSearchDao() }
    single { get<MovieDatabase>().tvSaveDetailDao() }
    single { get<MovieDatabase>().userProfileDao() }
}
/*
val fabricModule = module {
    single { injectFabric(get()) }
}

private fun injectFabric(app: Application) {
    Fabric.with(app, Crashlytics())
}*/

val preferenceHelperModule = module {
    single { PreferenceHelper(get()) }
}

val dataSourceModule = module {
    single { MovieRemoteDataSourceImpl(get()) as MovieRemoteDataSource }
    single { MovieCacheDataSourceImpl(get()) as MovieCacheDataSource }
    single { TvRemoteDataSourceImpl(get()) as TvRemoteDataSource }
    single { TvCacheDataSourceImpl(get()) as TvCacheDataSource }
    single { UserRemoteDataSourceImpl() as UserProfileRemoteDataSource }
    single { UserCacheDataSourceImpl(get()) as UserProfileCacheDataSource }
}

val repositoryModule = module {
    single { MovieRepositoryImpl(remoteSource = get(), localSource = get()) as MovieRepository }
    single { TvRepositoryImpl(remoteSource = get(), localSource = get()) as TvRepository }
    single { UserRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as UserRepository }
}

private val usecaseModule = module {
    factory { MovieUseCase(get()) }
    factory { TvShowUseCase(get()) }
    factory { UserUseCase(get()) }
}
