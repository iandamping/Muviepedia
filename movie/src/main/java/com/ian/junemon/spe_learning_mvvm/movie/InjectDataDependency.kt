package com.ian.junemon.spe_learning_mvvm.movie

import com.ian.junemon.spe_learning_mvvm.movie.movie.ui.MovieDataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */
val movieViewModelModule = module {
    viewModel { MovieDataViewModel(get()) }
}

