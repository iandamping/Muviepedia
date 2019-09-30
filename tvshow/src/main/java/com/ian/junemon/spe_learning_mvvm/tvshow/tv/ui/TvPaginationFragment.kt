package com.ian.junemon.spe_learning_mvvm.tvshow.tv.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.ian.app.helper.util.checkConnectivityStatus
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.junemon.spe_learning_mvvm.data.util.TvShowDetailConstant.localTvPopularPaginationAdapterCallback
import com.ian.junemon.spe_learning_mvvm.data.util.TvShowDetailConstant.localTvTopRatedPaginationAdapterCallback
import com.ian.junemon.spe_learning_mvvm.data.util.TvShowDetailConstant.popularTv
import com.ian.junemon.spe_learning_mvvm.data.util.TvShowDetailConstant.topRatedTv
import com.ian.junemon.spe_learning_mvvm.tvshow.R
import com.ian.junemon.spe_learning_mvvm.tvshow.databinding.FragmentPagingBinding
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.item_paging.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvPaginationFragment : Fragment() {
    private val vm: TvDataViewModel by viewModel()
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
