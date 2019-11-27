package com.ian.app.muviepedia.tvshow.di

import com.ian.app.muviepedia.tvshow.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
val tvModule = module {
    viewModel { TvViewModel(get()) }
}