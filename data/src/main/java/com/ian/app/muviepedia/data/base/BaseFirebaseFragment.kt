package com.ian.app.muviepedia.data.base

import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.ian.app.muviepedia.data.util.UtilConstant.requestSignIn

abstract class BaseFirebaseFragment : Fragment() {

    private val gmailLoginProvider = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
    )
    private val facebookLoginProvider = arrayListOf(
            AuthUI.IdpConfig.FacebookBuilder().build())


    protected fun createFacebookSignInIntent() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(facebookLoginProvider)
                        .build(),
                requestSignIn
        )
    }

    protected fun createGmailSignInIntent() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(gmailLoginProvider)
                        .build(),
                requestSignIn
        )
    }
}