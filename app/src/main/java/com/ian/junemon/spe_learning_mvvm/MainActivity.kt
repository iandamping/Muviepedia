package com.ian.junemon.spe_learning_mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.MovieViewmodel
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.tv.TvViewmodel
import com.ian.junemon.spe_learning_mvvm.util.logE
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val movieVm: MovieViewmodel by viewModel()
    private val tvVM: TvViewmodel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testTV()
    }

    private fun testTV() {
        tvVM.multipleData.observe(this@MainActivity, Observer {
            it.first.first.forEach {singleData ->
                logE(singleData.name)
            }
            it.first.second.forEach {singleData ->
                logE(singleData.name)
            }
            it.second.forEach {singleData ->
                logE(singleData.name)
            }
        })

        tvVM.multiplePairredData.observe(this@MainActivity, Observer {
            it.first.forEach {singleData ->
                logE(singleData.name + " ini single data")
            }
            it.second.forEach {singleData ->
                logE(singleData.name + " ini single data")
            }
        })
    }

    fun tesMovie() {
        movieVm.data.observe(this@MainActivity, Observer {
            it.forEach { singleData ->
                logE(singleData.original_title)
            }
        })
    }
}
