package com.ian.junemon.spe_learning_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.tv.TvViewmodel
import com.ian.junemon.spe_learning_mvvm.databinding.ActivityMainBinding
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.imageFormatter
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.tvDiffCallbacks
import com.ian.junemon.spe_learning_mvvm.util.setUpVerticalListAdapterWithSlideLeft
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val tvVM: TvViewmodel by viewModel()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.uiState = tvVM
//        setContentView(R.layout.activity_main)
//        jobError()
        getData()
//        testingChannel()
    }

    private fun getData() {
        tvVM.singleListData.observe(this, Observer { data ->
            binding.listData = data
            rvData.setUpVerticalListAdapterWithSlideLeft(data, tvDiffCallbacks, R.layout.item_movie, {
                ivPosterPath.loadWithGlide(imageFormatter + it.poster_path)
                tvMovieTittles.text = it.name
            })
        })
    }

    /*private fun jobError() = runBlocking {
        launch(NonCancellable) {
            *//*Keep run but still force close*//*
            repeat(7) {
                println("coroutine 1: $it")
                delay(500)
            }
        }
        launch(Dispatchers.Default) {
            *//*Stop run when coroutineContext cancel*//*
            repeat(7) {
                println("coroutine 2: $it")
                delay(500)
            }
        }
        delay(2000).also {
            coroutineContext[Job]?.cancel()
        }
    }*/


    /*private fun testingChannel() = runBlocking {
        val channel = Channel<Int>()

        launch {
            for (i in 1..10) {
                channel.send(i)
            }
            channel.close()

        }

        launch {
            println("consume indexed")
            channel.consumeEach {
                println("received channel: $it")

            }

            println("consume each")
            channel.consumeEach {
                println("received channel: $it")

            }
        }


        *//*for (i in channel) {
            println("received channel: $i")
        }*//*
    }*/


}
