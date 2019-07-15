package com.ian.junemon.spe_learning_mvvm.di

import com.ian.junemon.spe_learning_mvvm.data.repo.movie.MovieRepository
import com.ian.junemon.spe_learning_mvvm.data.repo.tv.TvRepository
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
val repositoryModule = module {
    single { MovieRepository(get()) }
    single { TvRepository(get()) }
}