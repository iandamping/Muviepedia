package com.ian.app.muviepedia.repository

import androidx.lifecycle.LiveData
import com.ian.app.muviepedia.model.profile.UserProfileData

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface UserRepository {

    fun get(): LiveData<UserProfileData>

    fun initOnResume()

    fun initOnPause()

    suspend fun initLogout()
}