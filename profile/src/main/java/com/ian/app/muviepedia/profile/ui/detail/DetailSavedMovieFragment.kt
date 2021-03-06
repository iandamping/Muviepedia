package com.ian.app.muviepedia.profile.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.ian.app.helper.util.fullScreen
import com.ian.app.muviepedia.profile.MovieViewModel
import com.ian.app.muvipedia.presentation.util.intentShareImageAndText
import com.ian.app.muvipedia.presentation.util.saveImage
import com.ian.app.muviepedia.profile.R
import com.ian.app.muviepedia.profile.databinding.FragmentSavedMovieDetailBinding
import com.ian.app.muvipedia.presentation.model.movie.MovieLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.movie.mapToPresentation
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSavedMovieFragment : Fragment() {
    private val movieVm: MovieViewModel by viewModel()
    private var idForDeleteItem: Int? = null
    private var isFavorite: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val argumentId by lazy { DetailSavedMovieFragmentArgs.fromBundle(arguments!!) }
        val binding: FragmentSavedMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_movie_detail, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            consumeDetailData(argumentId.detailSavedId, this)
        }
        return binding.root
    }
    private fun consumeDetailData(movieId: Int, binding: FragmentSavedMovieDetailBinding) {
        binding.apply {
            movieVm.getDetailSavedMovieByID(movieId).observe(viewLifecycleOwner, Observer { result ->
                if (result != null) {
                    detailData = result.mapToPresentation()
                    inflateView(this@apply, result.mapToPresentation())

                    consumeSaveDetailData(result.mapToPresentation(), this)
                } else {
                    this@apply.root.findNavController().navigateUp()
                }
            })
        }
    }

    private fun inflateView(binding: FragmentSavedMovieDetailBinding, data: MovieLocalSaveDetailPresentation?) {
        binding.apply {
            ivDetailMovieImages.setOnClickListener {
                it.context.fullScreen(data?.poster_path)
            }
            ivShare.setOnClickListener {
                activity?.intentShareImageAndText(movieVm.viewModelScope, data?.title!!, data.overview, data.poster_path!!)
            }
            ivBookmark.setOnClickListener {
                if (data != null)
                    if (isFavorite) {
                        if (idForDeleteItem != null) movieVm.deleteSelectedMovie(idForDeleteItem!!)
                    } else {
                        movieVm.saveDetailMovieData(data)
                    }
            }
            ivDownload.setOnClickListener {
                activity?.saveImage(movieVm.viewModelScope, coordinatorParent, data?.poster_path)
            }
        }
    }

    private fun consumeSaveDetailData(detailState: MovieLocalSaveDetailPresentation, binding: FragmentSavedMovieDetailBinding) {
        binding.apply {
            movieVm.consumeSaveDetailMovie.observe(viewLifecycleOwner, Observer { result ->
                if (!result.isNullOrEmpty()) {
                    result.forEach {
                        if (it.id == detailState.id) {
                            idForDeleteItem = it.id
                            isFavorite = true
                            bookmarkedState = isFavorite
                        } else {
                            isFavorite = false
                            bookmarkedState = isFavorite
                        }
                    }
                } else {
                    isFavorite = false
                    bookmarkedState = isFavorite
                }
            })
        }
    }
}