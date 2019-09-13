package com.ian.junemon.spe_learning_mvvm.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.junemon.spe_learning_mvvm.BuildConfig.imageFormatter
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.MoviePagingViewmodel
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentPagingBinding
import com.ian.junemon.spe_learning_mvvm.util.movieAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.item_paging.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class PaginationFragment : Fragment() {
    private val vm: MoviePagingViewmodel by viewModel()
    private lateinit var binding: FragmentPagingBinding
    private var paginationState = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_paging, container, false)
        val args = PaginationFragmentArgs.fromBundle(arguments!!)
        initFetchNetworkData(args.movieType, binding)
        return binding.root
    }


    private fun initFetchNetworkData(state: String, binding: FragmentPagingBinding) {
        vm.getAllMovies(state).observe(this@PaginationFragment.viewLifecycleOwner, Observer { data ->
            if (data != null) {
                paginationState = true
                binding.rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_paging, 2, {
                    ivDiscoverMovie.loadResizeWithGlide(imageFormatter + it.poster_path)
                }, movieAdapterCallback)
                if (binding.shimmerGridListContainer.isShimmerStarted && binding.shimmerGridListContainer.isShimmerVisible) {
                    binding.shimmerGridListContainer.stopShimmer()
                    binding.shimmerGridListContainer.hideShimmer()
                    binding.shimmerGridListContainer.gone()
                }
            }
        })
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
