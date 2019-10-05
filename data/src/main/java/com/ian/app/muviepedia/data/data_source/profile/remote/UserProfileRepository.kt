package com.ian.app.muviepedia.data.data_source.profile.remote

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.ian.app.muviepedia.data.data_source.profile.local.UserProfileDataSource
import com.ian.app.muviepedia.data.data_source.profile.local.model.UserProfileData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
class UserProfileRepository(private val localSource: UserProfileDataSource) {
    private val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var listener: FirebaseAuth.AuthStateListener

    val userData by lazy { localSource.getUserData }


    fun getUserData(scope: CoroutineScope) {
        listener = FirebaseAuth.AuthStateListener {
            if (mFirebaseAuth.currentUser != null) {
                scope.launch {
                    localSource.saveUserData(UserProfileData(
                            null,
                            mFirebaseAuth.currentUser?.uid,
                            mFirebaseAuth.currentUser?.photoUrl.toString(),
                            mFirebaseAuth.currentUser?.displayName,
                            mFirebaseAuth.currentUser?.email
                    ))
                }
            }

        }
    }

    fun initOnResume() {
        if (::listener.isInitialized) mFirebaseAuth.addAuthStateListener(listener)
    }

    fun initOnPause() {
        if (::listener.isInitialized) mFirebaseAuth.removeAuthStateListener(listener)
    }

    suspend fun logOut(ctx: Context) {
        AuthUI.getInstance().signOut(ctx)
        localSource.clearAllUserData()
    }


}