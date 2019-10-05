package com.ian.app.muviepedia.data.data_source.profile.ui

import android.content.Context
import androidx.lifecycle.liveData
import com.ian.app.muviepedia.data.base.BaseViewModel
import com.ian.app.muviepedia.data.data_source.profile.remote.UserProfileRepository
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
class UserProfileViewModel(private val repo: UserProfileRepository): BaseViewModel() {

    val getUserData = liveData {
        emitSource(repo.userData)
    }

    val initOnResume by lazy { repo.initOnResume() }

    val initOnPause by lazy { repo.initOnPause() }

    fun inflateFirebaseLogin(){
        vmScopes.launch {
            repo.getUserData(this)
        }
    }

    fun logout(context: Context?){
        if(context!=null){
            vmScopes.launch {
                repo.logOut(context)
            }

        }
    }

    init {
        repo.getUserData(vmScopes)
    }
}