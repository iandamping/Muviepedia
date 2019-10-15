package com.ian.app.muviepedia.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.ian.app.muviepedia.data.base.BaseFirebaseFragment
import com.ian.app.muviepedia.data.data_source.profile.ui.UserProfileViewModel
import com.ian.app.muviepedia.profile.R
import com.ian.app.muviepedia.profile.databinding.FragmentProfilesBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
class ProfileFragments : BaseFirebaseFragment() {
    private val vm: UserProfileViewModel by viewModel()

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
                userProfile = it
            })
        }
    }

    private fun inflateView(binding: FragmentProfilesBinding){
        binding.apply {
            lnGmailLogin.setOnClickListener {
                createGmailSignInIntent()
                vm.inflateFirebaseLogin()
            }
            btnLogout.setOnClickListener {
                vm.logout(it.context)
            }

            lnProfileSavedMovie.setOnClickListener {
                it.findNavController().navigate(ProfileFragmentsDirections.actionProfileFragmentsToMovieSavedFragment())
            }

            lnProfileSavedTv.setOnClickListener {
                it.findNavController().navigate(ProfileFragmentsDirections.actionProfileFragmentsToTvshowSavedFragment())
            }
        }
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