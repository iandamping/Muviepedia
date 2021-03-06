package com.ian.app.muviepedia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.visible
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initBottomNav(binding)
    }

    private fun initBottomNav(binding: ActivityMainBinding) {
        binding.apply {
            with(Navigation.findNavController(this@MainActivity, R.id.learnNavHostFragment)) {
                bottomNav.setupWithNavController(this@with)
                addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.homeFragment -> bottomNav.visible()
                        R.id.tvFragment -> bottomNav.visible()
                        R.id.profileFragments -> bottomNav.visible()
                        else -> bottomNav.gone()
                    }
                }
            }
        }
    }
}
