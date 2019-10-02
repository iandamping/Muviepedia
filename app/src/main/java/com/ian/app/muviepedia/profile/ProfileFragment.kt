package com.ian.app.muviepedia.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.FragmentProfileBinding
import com.ian.app.muviepedia.util.ProfileConstant

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.ivProfile.loadWithGlide(ProfileConstant.myFaceUrl)
        return binding.root
    }


}
