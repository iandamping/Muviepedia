package com.ian.junemon.spe_learning_mvvm.movie.movie.ui


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
import com.ian.junemon.spe_learning_mvvm.data.data.ResultToConsume
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.ui.MovieDataViewModel
import com.ian.junemon.spe_learning_mvvm.data.util.MovieDetailConstant.searchMovieAdapterCallback
import com.ian.junemon.spe_learning_mvvm.movie.R
import com.ian.junemon.spe_learning_mvvm.movie.databinding.FragmentMovieSearchBinding
import com.ian.recyclerviewhelper.helper.setUpVerticalGridAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieSearchFragment : Fragment() {
    private val vms: MovieDataViewModel by viewModel()
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
                    when (result.status) {
                        ResultToConsume.Status.SUCCESS -> {
                            progressSearch.gone()
                            rvSearch.setUpVerticalGridAdapter(result.data, searchMovieAdapterCallback, R.layout.item_movie, 2, {
                                ivHomeMovie.loadWithGlide(it.poster_path)
                                tvHomeMovieName.text = it.title
                            },{
                               if(id!=null) this@apply.root.findNavController().navigate(MovieSearchFragmentDirections.actionSearchFragmentToHomeFragment(id!!))
                            })
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
