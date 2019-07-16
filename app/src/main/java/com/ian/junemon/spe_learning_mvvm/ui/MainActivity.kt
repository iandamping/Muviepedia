package com.ian.junemon.spe_learning_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.tv.TvViewmodel
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.imageFormatter
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.tvDiffCallbacks
import com.ian.junemon.spe_learning_mvvm.util.setUpVertical
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val tvVM: TvViewmodel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData() {
        tvVM.singleData.observe(this, Observer {
            rvData.setUpVertical(it, tvDiffCallbacks, R.layout.item_movie, {
                with(this) {
                    ivPosterPath.loadWithGlide(imageFormatter + it.poster_path)
                    tvMovieTittles.text = it.name
                }
            })
        })
    }

}
