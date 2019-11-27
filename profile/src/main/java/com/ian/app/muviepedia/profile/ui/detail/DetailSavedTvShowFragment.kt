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
import com.ian.app.muvipedia.presentation.util.intentShareImageAndText
import com.ian.app.muvipedia.presentation.util.saveImage
import com.ian.app.muviepedia.profile.R
import com.ian.app.muviepedia.profile.TvViewModel
import com.ian.app.muviepedia.profile.databinding.FragmentSavedTvshowDetailBinding
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.mapToPresentation
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSavedTvShowFragment : Fragment() {
    private val tvshowVm: TvViewModel by viewModel()
    private var idForDeleteItem: Int? = null
    private var isFavorite: Boolean = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val argumentId by lazy { DetailSavedTvShowFragmentArgs.fromBundle(arguments!!) }
        val binding: FragmentSavedTvshowDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_tvshow_detail, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            consumeDetailData(argumentId.detailSavedID, this)
        }
        return binding.root
    }

    private fun consumeDetailData(movieId: Int, binding: FragmentSavedTvshowDetailBinding) {
        binding.apply {
            tvshowVm.loadAllTvShowDataById(movieId).observe(viewLifecycleOwner, Observer { result ->
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

    private fun inflateView(binding: FragmentSavedTvshowDetailBinding, data: TvLocalSaveDetailPresentation?) {
        binding.apply {
            ivDetailTvImages.setOnClickListener {
                it.context.fullScreen(data?.poster_path)
            }
            ivShare.setOnClickListener {
                activity?.intentShareImageAndText(tvshowVm.viewModelScope, data?.name!!, data.overview, data.poster_path!!)
            }
            ivBookmark.setOnClickListener {
                if (data != null)
                    if (isFavorite) {
                        if (idForDeleteItem != null) tvshowVm.deleteSelectedMovie(idForDeleteItem!!)
                    } else {
                        tvshowVm.saveDetailMovieData(data)
                    }
            }
            ivDownload.setOnClickListener {
                activity?.saveImage(tvshowVm.viewModelScope, nestedParent, data?.poster_path)
            }
        }
    }

    private fun consumeSaveDetailData(detailState: TvLocalSaveDetailPresentation, binding: FragmentSavedTvshowDetailBinding) {
        binding.apply {
            tvshowVm.consumeSaveDetailTv.observe(viewLifecycleOwner, Observer { result ->
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