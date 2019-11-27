package com.ian.app.muviepedia.movie.movie.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.visible
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muviepedia.movie.MovieViewModel
import com.ian.app.muviepedia.movie.R
import com.ian.app.muviepedia.movie.databinding.FragmentMovieBinding
import com.ian.app.muviepedia.movie.movie.ui.slider.SliderMovieAdapter
import com.ian.app.muviepedia.movie.util.postRunnable
import com.ian.app.muvipedia.presentation.model.movie.mapToPresentation
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.localMoviePopularAdapterCallback
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.localMovieUpComingAdapterCallback
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.movieDelayMillis
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.popularMovie
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.upcomingMovie
import com.ian.recyclerviewhelper.helper.setUpHorizontalListAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val vm: MovieViewModel by viewModel()
    private var mHandler: Handler = Handler()
    private var pageSize: Int? = 0
    private var currentPage = 0

    private fun slideRunnable(binding: FragmentMovieBinding) = object : Runnable {
        override fun run() {
            if (currentPage == pageSize) {
                currentPage = 0
            }
            binding.vpNowPlaying.setCurrentItem(currentPage++, true)
            mHandler.postDelayed(this, movieDelayMillis)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
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

    private fun consumeNowPlayingMovie(binding: FragmentMovieBinding) {
        binding.apply {
            vm.nowPlayingMovie.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {

                            pageSize = if (result.data!!.size > 10) {
                                10
                            } else result.data!!.size

                            vpNowPlaying.visible()
                            vpNowPlaying.adapter = SliderMovieAdapter(result.data?.mapToPresentation()?.take(10)!!)
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

    private fun consumePopularMovie(binding: FragmentMovieBinding) {
        binding.apply {
            vm.popularMovie.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {
                            rvPopularMovie.setUpHorizontalListAdapter(result.data?.mapToPresentation(), localMoviePopularAdapterCallback, R.layout.item_movie, {
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
                it.findNavController().navigate(MovieFragmentDirections.actionMovieNavigationToPaginationFragment(popularMovie))
            }
        }
    }

    private fun consumeUpComingMovie(binding: FragmentMovieBinding) {
        binding.apply {
            vm.upComingMovie.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {
                            rvUpComingMovie.setUpHorizontalListAdapter(result.data?.mapToPresentation(), localMovieUpComingAdapterCallback, R.layout.item_movie, {
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
                it.findNavController().navigate(MovieFragmentDirections.actionMovieNavigationToPaginationFragment(upcomingMovie))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (::binding.isInitialized) mHandler.postRunnable(slideRunnable(binding))
    }

    override fun onStop() {
        super.onStop()
        if (::binding.isInitialized) mHandler.removeCallbacks(slideRunnable(binding))
    }
}
