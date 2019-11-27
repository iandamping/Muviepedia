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
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.savedTvShowAdapterCallback
import com.ian.app.muviepedia.profile.R
import com.ian.app.muviepedia.profile.TvViewModel
import com.ian.app.muviepedia.profile.databinding.FragmentSavedTvshowBinding
import com.ian.app.muvipedia.presentation.model.tvshow.mapToPresentation
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import kotlinx.android.synthetic.main.item_similar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class TvshowSavedFragment : Fragment() {
    private val vm: TvViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSavedTvshowBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_tvshow, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            inflateSavedMovie(this)
        }
        return binding.root
    }

    private fun inflateSavedMovie(binding: FragmentSavedTvshowBinding) {
        binding.apply {
            vm.consumeSaveDetailTv.observe(viewLifecycleOwner, Observer { result ->
                if (result.isEmpty()) {
                    emptyState = true
                } else {
                    emptyState = false
                    rvSavedTvShow.setUpVerticalListAdapter(result.mapToPresentation(), savedTvShowAdapterCallback, R.layout.item_similar, {
                        ivSimilarMovie.loadWithGlide(it.poster_path)
                        tvSimilarMovieTittle.text = it.name
                        tvSimilarMovieReleaseDate.text = it.first_air_date
                    }, {
                        if (id != null) {
                            this@apply.root.findNavController().navigate(TvshowSavedFragmentDirections.actionTvshowSavedFragmentToDetailSavedTvShowFragment(localID!!))
                        }
                    })
                }
            })
        }
    }
}