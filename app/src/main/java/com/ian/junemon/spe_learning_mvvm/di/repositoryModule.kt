package com.ian.junemon.spe_learning_mvvm.di

import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteRepository
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteRepository
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
val repositoryModule = module {
    single { MovieRemoteRepository(get(), get()) }
    single { TvRemoteRepository(get(), get()) }
}