package com.ian.junemon.spe_learning_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.gone
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.toolbar.gone()
//        binding.toolbar.title = " Spe learn coroutine"
    }


}
