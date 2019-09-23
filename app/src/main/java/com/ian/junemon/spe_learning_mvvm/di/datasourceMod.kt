package com.ian.junemon.spe_learning_mvvm.di

import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteDataSource
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */

val allSourceModule = module {
    factory { MovieRemoteDataSource(get()) }
}