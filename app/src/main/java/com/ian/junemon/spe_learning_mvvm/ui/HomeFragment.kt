package com.ian.junemon.spe_learning_mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.MovieViewmodel
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentHomeBinding
import com.ian.junemon.spe_learning_mvvm.ui.slider.SliderMovieAdapter
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant
import com.ian.junemon.spe_learning_mvvm.util.movieAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import com.ian.recyclerviewhelper.helper.setUpHorizontalListAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val vm: MovieViewmodel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vm.workConcurrents()
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        consumeData(binding)
        binding.movieViewmodel = vm
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun consumeData(binding: FragmentHomeBinding) {
        vm.concurentData.observe(this@HomeFragment.viewLifecycleOwner, Observer { movieData ->
            binding.apply {
                vpNowPlaying.adapter = SliderMovieAdapter(movieData.first)
                indicator.setViewPager(binding.vpNowPlaying)
                rvPopularMovie.setUpHorizontalListAdapter(movieData.second, movieAdapterCallback, R.layout.item_movie, {
                    ivHomeMovie.loadWithGlide(imageFormatter + it.poster_path)
                    tvHomeMovieName.text = it.original_title
                }, {
                    if (id != null) findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(id!!))
                })
                rvUpComingMovie.setUpHorizontal(movieData.third, R.layout.item_movie, {
                    ivHomeMovie.loadWithGlide(imageFormatter + it.poster_path)
                    tvHomeMovieName.text = it.original_title
                }, {
                    if (id != null) findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(id!!))
                })
                invalidateAll()

                tvSeeAllPopularMovie.setOnClickListener {
                    it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPaginationFragment(MovieConstant.popularPagingState))
                }

                tvSeeAllUpComingMovie.setOnClickListener {
                    it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPaginationFragment(MovieConstant.upcomingPagingState))

                }
                llSearch.setOnClickListener {
                    it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())

                }
            }

        })
    }

}
