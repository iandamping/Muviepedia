package com.ian.junemon.spe_learning_mvvm.tvshow

import com.ian.junemon.spe_learning_mvvm.tvshow.tv.ui.TvDataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */
val tvViewModelModule = module {
    viewModel { TvDataViewModel(get()) }
}
