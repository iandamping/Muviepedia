package com.ian.junemon.spe_learning_mvvm.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.ResultToConsume
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentHomeBinding
import com.ian.junemon.spe_learning_mvvm.movie.ui.slider.SliderMovieAdapter
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.localMoviePopularAdapterCallback
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.localMovieUpComingAdapterCallback
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularMovie
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.upcomingMovie
import com.ian.recyclerviewhelper.helper.setUpHorizontalListAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val vm: MovieDataViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.apply {
            lifecycleOwner = this@MovieFragment
            consumeNowPlayingMovie(this)
            consumePopularMovie(this)
            consumeUpComingMovie(this)
            llSearch.setOnClickListener {
                it.findNavController().navigate(MovieFragmentDirections.actionHomeFragmentToSearchFragment())

            }
        }
        return binding.root
    }


    private fun consumeNowPlayingMovie(binding: FragmentHomeBinding) {
        binding.apply {
            vm.nowPlayingMovie.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {
                            vpNowPlaying.adapter = SliderMovieAdapter(result.data)
                            indicator.setViewPager(binding.vpNowPlaying)

                            if (shimmerSlider.isShimmerStarted && shimmerSlider.isShimmerVisible) {
                                shimmerSlider.stopShimmer()
                                shimmerSlider.hideShimmer()
                                shimmerSlider.gone()
                            }
                        }
                    }
                    ResultToConsume.Status.LOADING -> shimmerSlider.startShimmer()

                    ResultToConsume.Status.ERROR -> {
                        if (shimmerSlider.isShimmerStarted && shimmerSlider.isShimmerVisible) {
                            shimmerSlider.stopShimmer()
                            shimmerSlider.hideShimmer()
                            shimmerSlider.gone()
                        }
                        Snackbar.make(lnMovieFragment, result.message!!, Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    private fun consumePopularMovie(binding: FragmentHomeBinding) {
        binding.apply {
            vm.popularMovie.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {
                            rvPopularMovie.setUpHorizontalListAdapter(result.data, localMoviePopularAdapterCallback, R.layout.item_movie, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.title
                            }, { if (id != null) findNavController().navigate(MovieFragmentDirections.actionHomeFragmentToDetailMovieFragment(id!!)) })

                            if (shimmerPopularMovie.isShimmerStarted && shimmerPopularMovie.isShimmerVisible) {
                                shimmerPopularMovie.stopShimmer()
                                shimmerPopularMovie.hideShimmer()
                                shimmerPopularMovie.gone()
                            }
                        }
                    }
                    ResultToConsume.Status.LOADING -> shimmerPopularMovie.startShimmer()

                    ResultToConsume.Status.ERROR -> {
                        if (shimmerPopularMovie.isShimmerStarted && shimmerPopularMovie.isShimmerVisible) {
                            shimmerPopularMovie.stopShimmer()
                            shimmerPopularMovie.hideShimmer()
                            shimmerPopularMovie.gone()
                        }
                    }
                }
            })
            tvSeeAllPopularMovie.setOnClickListener {
                it.findNavController().navigate(MovieFragmentDirections.actionHomeFragmentToPaginationFragment(popularMovie))
            }
        }
    }

    private fun consumeUpComingMovie(binding: FragmentHomeBinding) {
        binding.apply {
            vm.upComingMovie.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {
                            rvUpComingMovie.setUpHorizontalListAdapter(result.data, localMovieUpComingAdapterCallback, R.layout.item_movie, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.title
                            }, { if (id != null) findNavController().navigate(MovieFragmentDirections.actionHomeFragmentToDetailMovieFragment(id!!)) })

                            if (shimmmerUpComingMovie.isShimmerStarted && shimmmerUpComingMovie.isShimmerVisible) {
                                shimmmerUpComingMovie.stopShimmer()
                                shimmmerUpComingMovie.hideShimmer()
                                shimmmerUpComingMovie.gone()
                            }
                        }
                    }
                    ResultToConsume.Status.LOADING -> shimmmerUpComingMovie.startShimmer()

                    ResultToConsume.Status.ERROR -> {
                        if (shimmmerUpComingMovie.isShimmerStarted && shimmmerUpComingMovie.isShimmerVisible) {
                            shimmmerUpComingMovie.stopShimmer()
                            shimmmerUpComingMovie.hideShimmer()
                            shimmmerUpComingMovie.gone()
                        }
                    }
                }
            })
            tvSeeAllUpComingMovie.setOnClickListener {
                it.findNavController().navigate(MovieFragmentDirections.actionHomeFragmentToPaginationFragment(upcomingMovie))
            }
        }
    }


}
