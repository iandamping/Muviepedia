package com.ian.app.muviepedia.data.cleanarch.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.ian.app.muviepedia.data.cleanarch.data.datasource.UserProfileRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.UserProfileEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.profile.mapToDomain
import com.ian.app.muviepedia.model.profile.UserProfileData
import kotlinx.coroutines.CompletableDeferred

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class UserRemoteDataSourceImpl : UserProfileRemoteDataSource {
    private val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var listener: FirebaseAuth.AuthStateListener

    override suspend fun get(): UserProfileData {
        val result = CompletableDeferred<UserProfileEntity>()
        listener = FirebaseAuth.AuthStateListener {
            if (mFirebaseAuth.currentUser != null) {
                result.complete(
                        UserProfileEntity(
                            null,
                            mFirebaseAuth.currentUser?.uid,
                            mFirebaseAuth.currentUser?.photoUrl.toString(),
                            mFirebaseAuth.currentUser?.displayName,
                            mFirebaseAuth.currentUser?.email
                        )
                    )
            }
        }
        return result.await().mapToDomain()
    }

    override fun initOnResume() {
        if (::listener.isInitialized) mFirebaseAuth.addAuthStateListener(listener)
    }

    override fun initOnPause() {
        if (::listener.isInitialized) mFirebaseAuth.removeAuthStateListener(listener)
    }

    override suspend fun initLogout() {
        if (::listener.isInitialized) mFirebaseAuth.signOut()
    }
}