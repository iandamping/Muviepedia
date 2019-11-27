package com.ian.app.muviepedia.tvshow.tv.ui


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
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.localTvSearchAdapterCallback
import com.ian.app.muviepedia.tvshow.R
import com.ian.app.muviepedia.tvshow.TvViewModel
import com.ian.app.muviepedia.tvshow.databinding.FragmentTvSearchBinding
import com.ian.app.muvipedia.presentation.model.tvshow.mapToPresentation
import com.ian.recyclerviewhelper.helper.setUpVerticalGridAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvSearchFragment : Fragment() {
    private val vm: TvViewModel by viewModel()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTvSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_search, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            searchVm = vm
            searchData(this)
            invalidateAll()
        }
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun searchData(binding: FragmentTvSearchBinding) {
        binding.apply {
            vm.mutableEditText.observe(viewLifecycleOwner, Observer { querry ->
                vm.observeSearchData(querry).observe(viewLifecycleOwner, Observer { result ->

                    rvTvSearch.setUpVerticalGridAdapter(result.data?.mapToPresentation(), localTvSearchAdapterCallback, R.layout.item_movie, 2, {
                        ivHomeMovie.loadWithGlide(it.poster_path)
                        tvHomeMovieName.text = it.name
                    }, {
                        if (id != null) this@apply.root.findNavController().navigate(TvSearchFragmentDirections.actionTvSearchFragmentToTvDetailFragment(id!!))

                    })

                    when (result.status) {
                        com.ian.app.muviepedia.model.ResultToConsume.Status.SUCCESS -> {
                            progressTvSearch.gone()

                        }
                        com.ian.app.muviepedia.model.ResultToConsume.Status.LOADING -> progressTvSearch.visible()

                        com.ian.app.muviepedia.model.ResultToConsume.Status.ERROR -> {
                            progressTvSearch.gone()
                            tvSearchTvFailed.visible()
                            Snackbar.make(constraintParent, result.message!!, Snackbar.LENGTH_LONG).show()
                        }
                    }
                })
            })
        }

    }


}
