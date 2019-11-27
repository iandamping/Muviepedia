package com.ian.app.muviepedia.profile

import com.ian.app.muviepedia.usecase.UserUseCase
import com.ian.app.muvipedia.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
class UserProfileViewModel(private val repo: UserUseCase) : BaseViewModel() {

    val getUserData = repo.get()

    val initOnResume by lazy { repo.initOnResume() }

    val initOnPause by lazy { repo.initOnPause() }

    fun logout() {
        vmScopes.launch {
            repo.initLogout()
        }
    }
}