package com.ian.junemon.spe_learning_mvvm.tv.ui


import android.os.Bundle
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
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.ResultToConsume
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentTvBinding
import com.ian.junemon.spe_learning_mvvm.movie.ui.MovieFragmentDirections
import com.ian.junemon.spe_learning_mvvm.movie.ui.slider.SliderMovieAdapter
import com.ian.junemon.spe_learning_mvvm.tv.ui.slider.SliderTvAdapter
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.localTopRatedAdapterCallback
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.localTvPopularAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpHorizontalListAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : Fragment() {
    private lateinit var binding: FragmentTvBinding
    private val vm: TvDataViewModel by viewModel()
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

    private fun consumeAiringToday(binding: FragmentTvBinding){
        binding.apply {
           vm.airingToday.observe(viewLifecycleOwner, Observer {result ->
               when(result.status){
                   ResultToConsume.Status.LOADING ->shimmerSliderTv.startShimmer()
                   ResultToConsume.Status.SUCCESS ->{
                       if (!result.data.isNullOrEmpty()) {
                           vpNowPlayingTv.adapter = SliderTvAdapter(result.data)
                           indicator.setViewPager(vpNowPlayingTv)
                           vpNowPlayingTv.visible()

                           if (shimmerSliderTv.isShimmerStarted && shimmerSliderTv.isShimmerVisible) {
                               shimmerSliderTv.stopShimmer()
                               shimmerSliderTv.hideShimmer()
                               shimmerSliderTv.gone()
                           }
                       }
                   }
                   ResultToConsume.Status.ERROR ->{
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

    private fun consumePopularTv(binding: FragmentTvBinding){
        binding.apply {
            vm.popularTv.observe(viewLifecycleOwner, Observer {result ->
                when(result.status){
                    ResultToConsume.Status.LOADING ->shimmerPopulartv.startShimmer()
                    ResultToConsume.Status.SUCCESS ->{
                        if (!result.data.isNullOrEmpty()) {
                            rvPopulartv.setUpHorizontalListAdapter(result.data, localTvPopularAdapterCallback, R.layout.item_movie, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.name
                            })

                            if (shimmerPopulartv.isShimmerStarted && shimmerPopulartv.isShimmerVisible) {
                                shimmerPopulartv.stopShimmer()
                                shimmerPopulartv.hideShimmer()
                                shimmerPopulartv.gone()
                            }
                        }
                    }
                    ResultToConsume.Status.ERROR ->{
                        if (shimmerPopulartv.isShimmerStarted && shimmerPopulartv.isShimmerVisible) {
                            shimmerPopulartv.stopShimmer()
                            shimmerPopulartv.hideShimmer()
                            shimmerPopulartv.gone()
                        }
                    }
                }
            })
        }
    }

    private fun consumeTopRated(binding:FragmentTvBinding){
        binding.apply {
            vm.topRated.observe(viewLifecycleOwner, Observer {result ->
                when(result.status){
                    ResultToConsume.Status.LOADING ->shimmmerTopRatedTv.startShimmer()
                    ResultToConsume.Status.SUCCESS ->{

                        if (!result.data.isNullOrEmpty()) {
                            rvTopRatedtv.setUpHorizontalListAdapter(result.data, localTopRatedAdapterCallback, R.layout.item_movie, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.name
                            })

                            if (shimmmerTopRatedTv.isShimmerStarted && shimmmerTopRatedTv.isShimmerVisible) {
                                shimmmerTopRatedTv.stopShimmer()
                                shimmmerTopRatedTv.hideShimmer()
                                shimmmerTopRatedTv.gone()
                            }
                        }


                    }
                    ResultToConsume.Status.ERROR ->{
                        if (shimmmerTopRatedTv.isShimmerStarted && shimmmerTopRatedTv.isShimmerVisible) {
                            shimmmerTopRatedTv.stopShimmer()
                            shimmmerTopRatedTv.hideShimmer()
                            shimmmerTopRatedTv.gone()
                        }
                    }
                }
            })
        }
    }


}
