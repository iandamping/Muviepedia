package com.ian.junemon.spe_learning_mvvm.tv.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.visible
import com.ian.junemon.spe_learning_mvvm.BuildConfig
import com.ian.junemon.spe_learning_mvvm.R
import com.ian.junemon.spe_learning_mvvm.data.ResultToConsume
import com.ian.junemon.spe_learning_mvvm.databinding.FragmentTvSearchBinding
import com.ian.junemon.spe_learning_mvvm.util.TvConstant.tvSearchAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpVertical
import com.ian.recyclerviewhelper.helper.setUpVerticalGridAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvSearchFragment : Fragment() {
    private val vm: TvDataViewModel by viewModel()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTvSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_search, container, false)
        binding.apply {
            searchVm = vm
            lifecycleOwner = activity
            searchData(this)
        }
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun searchData(binding: FragmentTvSearchBinding) {
        binding.apply {
            vm.mutableEditText.observe(this@TvSearchFragment.viewLifecycleOwner, Observer { querry ->
                vm.observeSearchData(querry).observe(this@TvSearchFragment.viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        ResultToConsume.Status.SUCCESS -> {
                            progressTvSearch.gone()
                            rvTvSearch.setUpVerticalGridAdapter(result.data, tvSearchAdapterCallback, R.layout.item_movie, 2, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.name
                            })
                        }
                        ResultToConsume.Status.LOADING -> progressTvSearch.visible()

                        ResultToConsume.Status.ERROR -> {
                            progressTvSearch.gone()
                            tvSearchTvFailed.visible()
                            Snackbar.make(constraintParent, result.message!!, Snackbar.LENGTH_LONG).show()
                        }
                    }
                })
            })
            invalidateAll()
        }

    }


}
