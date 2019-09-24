package com.ian.junemon.spe_learning_mvvm.movie.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ian.app.helper.util.checkConnectivityStatus
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentPagingBinding
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.localMoviePopularPaginationAdapterCallback
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.localMovieUpComingPaginationAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.item_paging.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviePaginationFragment : Fragment() {
    private val vm: MovieDataViewModel by viewModel()
    private lateinit var binding: FragmentPagingBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_paging, container, false)
        val args = MoviePaginationFragmentArgs.fromBundle(arguments!!)
        initFetchNetworkData(args.movieType, binding)
        return binding.root
    }


    private fun initFetchNetworkData(state: String, binding: FragmentPagingBinding) {
        binding.apply {
            checkConnectivityStatus { connectivityState ->
                when (state) {
                    MovieConstant.popularMovie -> {
                        vm.observePopularPagination(connectivityState).observe(viewLifecycleOwner, Observer { data ->
                            rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                                ivDiscoverMovie.loadResizeWithGlide(it.poster_path)
                                tvDiscoverMovieDescription.text = it.title
                            }, localMoviePopularPaginationAdapterCallback)

                            if (shimmerGridListContainer.isShimmerStarted && shimmerGridListContainer.isShimmerVisible) {
                                shimmerGridListContainer.stopShimmer()
                                shimmerGridListContainer.hideShimmer()
                                shimmerGridListContainer.gone()
                            }

                        })
                    }

                    MovieConstant.upcomingMovie -> {
                        vm.observeUpComingPagination(connectivityState).observe(viewLifecycleOwner, Observer { data ->
                            rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                                ivDiscoverMovie.loadResizeWithGlide(it.poster_path)
                                tvDiscoverMovieDescription.text = it.title
                            }, localMovieUpComingPaginationAdapterCallback)

                            if (shimmerGridListContainer.isShimmerStarted && shimmerGridListContainer.isShimmerVisible) {
                                shimmerGridListContainer.stopShimmer()
                                shimmerGridListContainer.hideShimmer()
                                shimmerGridListContainer.gone()
                            }

                        })
                    }
                }

            }
        }
    }


    override fun onPause() {
        super.onPause()
        binding.shimmerGridListContainer.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerGridListContainer.startShimmer()
    }

}
