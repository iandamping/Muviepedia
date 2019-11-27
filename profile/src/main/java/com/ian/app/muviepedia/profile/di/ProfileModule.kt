package com.ian.app.muviepedia.profile.di

import com.ian.app.muviepedia.profile.MovieViewModel
import com.ian.app.muviepedia.profile.TvViewModel
import com.ian.app.muviepedia.profile.UserProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
val profileModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { UserProfileViewModel(get()) }
}