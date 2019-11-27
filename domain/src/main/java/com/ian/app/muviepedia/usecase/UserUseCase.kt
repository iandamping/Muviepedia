package com.ian.app.muviepedia.usecase

import androidx.lifecycle.LiveData
import com.ian.app.muviepedia.model.profile.UserProfileData
import com.ian.app.muviepedia.repository.UserRepository

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class UserUseCase(private val repo: UserRepository) {

    fun get(): LiveData<UserProfileData> = repo.get()

    fun initOnResume() = repo.initOnResume()

    fun initOnPause() = repo.initOnPause()

    suspend fun initLogout() = repo.initLogout()
}