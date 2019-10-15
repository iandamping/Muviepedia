package com.ian.app.muviepedia.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.muviepedia.data.BuildConfig
import com.ian.app.muviepedia.data.data_source.movie.data.ui.MovieDataViewModel
import com.ian.app.muviepedia.data.util.MovieDetailConstant.savedMovieAdapterCallback
import com.ian.app.muviepedia.profile.R
import com.ian.app.muviepedia.profile.databinding.FragmentSavedMovieBinding
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import kotlinx.android.synthetic.main.item_similar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieSavedFragment:Fragment() {
    private val vm:MovieDataViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding:FragmentSavedMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_movie,container,false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            inflateSavedMovie(binding)
        }
        return binding.root
    }

    private fun inflateSavedMovie(binding: FragmentSavedMovieBinding){
        binding.apply {
            vm.consumeSaveDetailMovie.observe(viewLifecycleOwner, Observer { result ->
                if(result.isEmpty()){
                    emptyState = true
                } else{
                    emptyState = false
                    rvSavedMovie.setUpVerticalListAdapter(result, savedMovieAdapterCallback, R.layout.item_similar, {
                        ivSimilarMovie.loadWithGlide(it.poster_path)
                        tvSimilarMovieTittle.text = it.title
                        tvSimilarMovieReleaseDate.text = it.release_date
                    }, {
                        if (id != null) {
                            this@apply.root.findNavController().navigate(MovieSavedFragmentDirections.actionMovieSavedFragmentToDetailSavedMovieFragment(localID!!))
                        }
                    })
                }

            })
        }
    }
}