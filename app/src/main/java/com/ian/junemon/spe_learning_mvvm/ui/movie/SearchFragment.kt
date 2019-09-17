package com.ian.junemon.spe_learning_mvvm.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ian.app.helper.util.loadWithGlide
import com.ian.junemon.spe_learning_mvvm.BuildConfig
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.MovieViewmodel
import com.ian.junemon.spe_learning_mvvm.databinding.ActivitySearchBinding
import com.ian.junemon.spe_learning_mvvm.util.movieAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpVerticalGridAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {
    private val vm: MovieViewmodel by viewModel()

    @FlowPreview
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ActivitySearchBinding = DataBindingUtil.inflate(inflater, R.layout.activity_search, container, false)
        binding.searchVm = vm
        searchData(binding)
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun searchData(binding: ActivitySearchBinding) {
        vm.mutableEditText.observe(this@SearchFragment.viewLifecycleOwner, Observer { querry ->
            vm.extractFlowData(querry).observe(this@SearchFragment.viewLifecycleOwner, Observer { data ->
                binding.rvSearch.setUpVerticalGridAdapter(data, movieAdapterCallback, R.layout.item_movie, 2, {
                    ivHomeMovie.loadWithGlide(BuildConfig.imageFormatter + it.poster_path)
                    tvHomeMovieName.text = it.original_title
                })
            })
        })
        binding.invalidateAll()
    }


}
