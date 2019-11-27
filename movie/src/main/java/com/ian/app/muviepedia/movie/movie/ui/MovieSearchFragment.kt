package com.ian.app.muviepedia.movie.movie.ui

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
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muviepedia.movie.MovieViewModel
import com.ian.app.muviepedia.movie.R
import com.ian.app.muviepedia.movie.databinding.FragmentMovieSearchBinding
import com.ian.app.muvipedia.presentation.model.movie.mapToPresentation
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.searchMovieAdapterCallback
import com.ian.recyclerviewhelper.helper.setUpVerticalGridAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieSearchFragment : Fragment() {
    private val vms: MovieViewModel by viewModel()
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_search, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            searchVm = vms
            searchData(this)
            invalidateAll()
        }
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun searchData(binding: FragmentMovieSearchBinding) {
        binding.apply {
            vms.movieSearchEditText.observe(this@MovieSearchFragment.viewLifecycleOwner, Observer { querry ->
                vms.observeSearchData(querry).observe(this@MovieSearchFragment.viewLifecycleOwner, Observer { result ->

                    rvSearch.setUpVerticalGridAdapter(result.data?.mapToPresentation(), searchMovieAdapterCallback, R.layout.item_movie, 2, {
                        ivHomeMovie.loadWithGlide(it.poster_path)
                        tvHomeMovieName.text = it.title
                    }, {
                        if (id != null) this@apply.root.findNavController().navigate(MovieSearchFragmentDirections.actionSearchFragmentToHomeFragment(id!!))
                    })

                    when (result.status) {
                        ResultToConsume.Status.SUCCESS -> {
                            progressSearch.gone()
                        }
                        ResultToConsume.Status.LOADING -> progressSearch.visible()

                        ResultToConsume.Status.ERROR -> {
                            progressSearch.gone()
                            Snackbar.make(constraintParent, result.message!!, Snackbar.LENGTH_LONG).show()
                        }
                    }
                })
            })
        }
    }
}
