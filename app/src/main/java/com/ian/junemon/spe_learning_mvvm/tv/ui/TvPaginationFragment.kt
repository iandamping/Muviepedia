package com.ian.junemon.spe_learning_mvvm.tv.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.ian.app.helper.util.checkConnectivityStatus
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentPagingBinding
import com.ian.junemon.spe_learning_mvvm.movie.ui.MoviePaginationFragmentArgs
import com.ian.junemon.spe_learning_mvvm.movie.ui.MoviePaginationFragmentDirections
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant
import com.ian.junemon.spe_learning_mvvm.util.TvConstant
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.localTvPopularPaginationAdapterCallback
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.localTvTopRatedPaginationAdapterCallback
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.popularTv
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.topRatedTv
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.item_paging.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvPaginationFragment : Fragment() {
    private val vm:TvDataViewModel by viewModel()
    private lateinit var binding: FragmentPagingBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_paging, container, false)
        val args = TvPaginationFragmentArgs.fromBundle(arguments!!)
        initFetchNetworkData(args.tvPaginationType, binding)
        return binding.root
    }
    private fun initFetchNetworkData(state: String, binding: FragmentPagingBinding) {
        binding.apply {
            checkConnectivityStatus { connectivityState ->
                when (state) {
                    popularTv -> {
                        vm.observePopularTvPagination(connectivityState).observe(viewLifecycleOwner, Observer { data ->
                            rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                                ivDiscoverMovie.loadResizeWithGlide(it.poster_path)
                                tvDiscoverMovieDescription.text = it.name
                            }, localTvPopularPaginationAdapterCallback,{
                                if(id!=null)this@apply.root.findNavController().navigate(TvPaginationFragmentDirections.actionTvPaginationFragmentToTvDetailFragment(id!!))
                            })

                            if (shimmerGridListContainer.isShimmerStarted && shimmerGridListContainer.isShimmerVisible) {
                                shimmerGridListContainer.stopShimmer()
                                shimmerGridListContainer.hideShimmer()
                                shimmerGridListContainer.gone()
                            }

                        })
                    }

                    topRatedTv -> {
                        vm.observeTopRatedPagination(connectivityState).observe(viewLifecycleOwner, Observer { data ->
                            rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                                ivDiscoverMovie.loadResizeWithGlide(it.poster_path)
                                tvDiscoverMovieDescription.text = it.name
                            }, localTvTopRatedPaginationAdapterCallback,{
                                if(id!=null)this@apply.root.findNavController().navigate(TvPaginationFragmentDirections.actionTvPaginationFragmentToTvDetailFragment(id!!))

                            })

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
