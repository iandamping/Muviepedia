package com.ian.app.muviepedia.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.ian.app.muviepedia.profile.MovieViewModel
import com.ian.app.muviepedia.profile.R
import com.ian.app.muviepedia.profile.TvViewModel
import com.ian.app.muviepedia.profile.UserProfileViewModel
import com.ian.app.muviepedia.profile.databinding.FragmentProfilesBinding
import com.ian.app.muvipedia.presentation.model.profile.mapToPresentation
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
class ProfileFragments : Fragment() {
    private val requestSignIn = 2341
    private val vm: UserProfileViewModel by viewModel()
    private val tvshowVm: TvViewModel by viewModel()
    private val movieVm: MovieViewModel by viewModel()
    private val gmailLoginProvider = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentProfilesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profiles, container, false)
        binding.apply {
            consumeProfileData(this@apply)
            inflateView(this@apply)
            invalidateAll()
        }

        return binding.root
    }

    private fun consumeProfileData(binding: FragmentProfilesBinding) {
        binding.apply {
            vm.getUserData.observe(viewLifecycleOwner, Observer {
                if (it != null) userProfile = it.mapToPresentation()
            })
        }
    }

    private fun inflateView(binding: FragmentProfilesBinding) {
        binding.apply {
            lnGmailLogin.setOnClickListener {
                createGmailSignInIntent()
            }
            btnLogout.setOnClickListener {
                vm.logout()
            }

            lnProfileSavedMovie.setOnClickListener {
                it.findNavController().navigate(ProfileFragmentsDirections.actionProfileFragmentsToMovieSavedFragment())
            }

            lnProfileSavedTv.setOnClickListener {
                it.findNavController().navigate(ProfileFragmentsDirections.actionProfileFragmentsToTvshowSavedFragment())
            }
            tvshowVm.consumeSaveDetailTv.observe(viewLifecycleOwner, Observer { result ->
                if (result.isNotEmpty()) {
                    tvShowTotalSave = "See all your ${result.size} tv show saved here"
                }
            })
            movieVm.consumeSaveDetailMovie.observe(viewLifecycleOwner, Observer { result ->
                if (result.isNotEmpty()) {
                    movieTotalSave = "See all your ${result.size} movie saved here"
                }
            })
            invalidateAll()
        }
    }

    private fun createGmailSignInIntent() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(gmailLoginProvider)
                .build(),
            requestSignIn
        )
    }

    override fun onResume() {
        super.onResume()
        vm.initOnResume
    }

    override fun onPause() {
        super.onPause()
        vm.initOnPause
    }
}