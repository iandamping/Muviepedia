package com.ian.junemon.spe_learning_mvvm.di

import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalDataSource
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteDataSource
import com.ian.junemon.spe_learning_mvvm.tv.data.local.TvLocalDataSource
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteDataSource
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */

val allSourceModule = module {
    single { MovieRemoteDataSource(get()) }
    single { MovieLocalDataSource(get()) }
    single { TvRemoteDataSource(get()) }
    single { TvLocalDataSource(get()) }
}