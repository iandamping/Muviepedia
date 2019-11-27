package com.ian.app.muviepedia.tvshow.tv.ui


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.visible
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.localTopRatedAdapterCallback
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.localTvPopularAdapterCallback
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.popularTv
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.topRatedTv
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.tvDelayMillis
import com.ian.app.muviepedia.tvshow.R
import com.ian.app.muviepedia.tvshow.TvViewModel
import com.ian.app.muviepedia.tvshow.databinding.FragmentTvBinding
import com.ian.app.muviepedia.tvshow.tv.ui.slider.SliderTvAdapter
import com.ian.app.muviepedia.tvshow.util.postRunnable
import com.ian.app.muvipedia.presentation.model.tvshow.mapToPresentation
import com.ian.recyclerviewhelper.helper.setUpHorizontalListAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : Fragment() {
    private lateinit var binding: FragmentTvBinding
    private val vm: TvViewModel by viewModel()
    private var mHandler: Handler = Handler()
    private var pageSize: Int? = 0
    private var currentPage = 0

    private fun slideRunnable(binding: FragmentTvBinding) = object : Runnable {
        override fun run() {
            if (currentPage == pageSize) {
                currentPage = 0
            }
            binding.vpNowPlayingTv.setCurrentItem(currentPage++, true)
            mHandler.postDelayed(this, tvDelayMillis)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv, container, false)
        binding.apply {
            lifecycleOwner = this@TvFragment.viewLifecycleOwner
            consumeAiringToday(this)
            consumePopularTv(this)
            consumeTopRated(this)
            llSearchTv.setOnClickListener {
                it.findNavController().navigate(TvFragmentDirections.actionTvFragmentToTvSearchFragment())
            }
        }
        return binding.root
    }

    private fun consumeAiringToday(binding: FragmentTvBinding) {
        binding.apply {
            vm.airingToday.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    com.ian.app.muviepedia.model.ResultToConsume.Status.LOADING -> shimmerSliderTv.startShimmer()
                    com.ian.app.muviepedia.model.ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {

                            pageSize = if (result.data!!.size > 10) {
                                10
                            } else result.data!!.size

                            vpNowPlayingTv.visible()
                            vpNowPlayingTv.adapter = SliderTvAdapter(result.data?.mapToPresentation()?.take(10)!!)
                            indicator.setViewPager(vpNowPlayingTv)

                            if (shimmerSliderTv.isShimmerStarted && shimmerSliderTv.isShimmerVisible) {
                                shimmerSliderTv.stopShimmer()
                                shimmerSliderTv.hideShimmer()
                                shimmerSliderTv.gone()
                            }
                        }
                    }
                    com.ian.app.muviepedia.model.ResultToConsume.Status.ERROR -> {
                        if (shimmerSliderTv.isShimmerStarted && shimmerSliderTv.isShimmerVisible) {
                            shimmerSliderTv.stopShimmer()
                            shimmerSliderTv.hideShimmer()
                            shimmerSliderTv.gone()
                        }
                        Snackbar.make(lnTvFragment, result.message!!, Snackbar.LENGTH_LONG).show()
                    }
                }

            })
        }
    }

    private fun consumePopularTv(binding: FragmentTvBinding) {
        binding.apply {
            vm.popularTv.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    com.ian.app.muviepedia.model.ResultToConsume.Status.LOADING -> shimmerPopulartv.startShimmer()
                    com.ian.app.muviepedia.model.ResultToConsume.Status.SUCCESS -> {
                        if (!result.data.isNullOrEmpty()) {
                            rvPopulartv.setUpHorizontalListAdapter(result.data?.mapToPresentation(), localTvPopularAdapterCallback, R.layout.item_movie, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.name
                            }, {
                                if (id != null) this@apply.root.findNavController().navigate(TvFragmentDirections.actionTvFragmentToTvDetailFragment(id!!))
                            })

                            if (shimmerPopulartv.isShimmerStarted && shimmerPopulartv.isShimmerVisible) {
                                shimmerPopulartv.stopShimmer()
                                shimmerPopulartv.hideShimmer()
                                shimmerPopulartv.gone()
                            }
                        }
                    }
                    com.ian.app.muviepedia.model.ResultToConsume.Status.ERROR -> {
                        if (shimmerPopulartv.isShimmerStarted && shimmerPopulartv.isShimmerVisible) {
                            shimmerPopulartv.stopShimmer()
                            shimmerPopulartv.hideShimmer()
                            shimmerPopulartv.gone()
                        }
                    }
                }
            })
            tvSeeAllPopularTv.setOnClickListener { it.findNavController().navigate(TvFragmentDirections.actionTvFragmentToTvPaginationFragment(popularTv)) }
        }

    }

    private fun consumeTopRated(binding: FragmentTvBinding) {
        binding.apply {
            vm.topRated.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    com.ian.app.muviepedia.model.ResultToConsume.Status.LOADING -> shimmmerTopRatedTv.startShimmer()
                    com.ian.app.muviepedia.model.ResultToConsume.Status.SUCCESS -> {

                        if (!result.data.isNullOrEmpty()) {
                            rvTopRatedtv.setUpHorizontalListAdapter(result.data?.mapToPresentation(), localTopRatedAdapterCallback, R.layout.item_movie, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.name
                            }, {
                                if (id != null) this@apply.root.findNavController().navigate(TvFragmentDirections.actionTvFragmentToTvDetailFragment(id!!))
                            })

                            if (shimmmerTopRatedTv.isShimmerStarted && shimmmerTopRatedTv.isShimmerVisible) {
                                shimmmerTopRatedTv.stopShimmer()
                                shimmmerTopRatedTv.hideShimmer()
                                shimmmerTopRatedTv.gone()
                            }
                        }


                    }
                    com.ian.app.muviepedia.model.ResultToConsume.Status.ERROR -> {
                        if (shimmmerTopRatedTv.isShimmerStarted && shimmmerTopRatedTv.isShimmerVisible) {
                            shimmmerTopRatedTv.stopShimmer()
                            shimmmerTopRatedTv.hideShimmer()
                            shimmmerTopRatedTv.gone()
                        }
                    }
                }
            })
            tvSeeAllTopRatedtv.setOnClickListener { it.findNavController().navigate(TvFragmentDirections.actionTvFragmentToTvPaginationFragment(topRatedTv)) }
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
