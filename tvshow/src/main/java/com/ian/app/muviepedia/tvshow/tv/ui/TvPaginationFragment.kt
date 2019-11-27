package com.ian.app.muviepedia.tvshow.tv.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.paging.PagedList
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.localTvPopularPaginationAdapterCallback
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.localTvTopRatedPaginationAdapterCallback
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.popularTv
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.topRatedTv
import com.ian.app.muviepedia.tvshow.R
import com.ian.app.muviepedia.tvshow.TvViewModel
import com.ian.app.muviepedia.tvshow.databinding.FragmentTvPagingBinding
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalPopularPaginationPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalTopRatedPaginationPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.mapToPresentation
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.item_paging.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvPaginationFragment : Fragment() {
    private val vm: TvViewModel by viewModel()
    private lateinit var binding: FragmentTvPagingBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_paging, container, false)
        val args = TvPaginationFragmentArgs.fromBundle(arguments!!)
        initFetchNetworkData(args.tvPaginationType, binding)
        return binding.root
    }

    private fun initFetchNetworkData(state: String, binding: FragmentTvPagingBinding) {
        binding.apply {
                when (state) {
                    popularTv -> {
                        vm.observePopularTvPagination().observe(viewLifecycleOwner, Observer { data ->
                            rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                                ivDiscoverMovie.loadResizeWithGlide(it.poster_path)
                                tvDiscoverMovieDescription.text = it.name
                            }, localTvPopularPaginationAdapterCallback, {
                                if (id != null) this@apply.root.findNavController().navigate(TvPaginationFragmentDirections.actionTvPaginationFragmentToTvDetailFragment(id!!))
                            })

                            if (shimmerGridListContainer.isShimmerStarted && shimmerGridListContainer.isShimmerVisible) {
                                shimmerGridListContainer.stopShimmer()
                                shimmerGridListContainer.hideShimmer()
                                shimmerGridListContainer.gone()
                            }

                        })
                    }

                    topRatedTv -> {
                        vm.observeTopRatedPagination().observe(viewLifecycleOwner, Observer { data ->
                            rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                                ivDiscoverMovie.loadResizeWithGlide(it.poster_path)
                                tvDiscoverMovieDescription.text = it.name
                            }, localTvTopRatedPaginationAdapterCallback, {
                                if (id != null) this@apply.root.findNavController().navigate(TvPaginationFragmentDirections.actionTvPaginationFragmentToTvDetailFragment(id!!))

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


    override fun onPause() {
        super.onPause()
        binding.shimmerGridListContainer.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerGridListContainer.startShimmer()
    }

}
