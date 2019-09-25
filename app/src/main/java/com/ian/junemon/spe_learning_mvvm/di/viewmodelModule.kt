package com.ian.junemon.spe_learning_mvvm.di


import com.ian.junemon.spe_learning_mvvm.movie.ui.MovieDataViewModel
import com.ian.junemon.spe_learning_mvvm.tv.ui.TvDataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */

val allVmModule = module {
    viewModel { MovieDataViewModel(get()) }
    viewModel { TvDataViewModel(get()) }
}

