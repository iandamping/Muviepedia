package com.ian.app.muviepedia.movie.di

import com.ian.app.muviepedia.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
val movieModule = module {
    viewModel { MovieViewModel(get()) }
}