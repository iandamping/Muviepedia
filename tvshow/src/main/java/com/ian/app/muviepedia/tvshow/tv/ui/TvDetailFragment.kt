package com.ian.app.muviepedia.tvshow.tv.ui


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.ian.app.helper.util.*
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.tvAdapterCallback
import com.ian.app.muvipedia.presentation.util.intentShareImageAndText
import com.ian.app.muvipedia.presentation.util.saveImage
import com.ian.app.muviepedia.tvshow.R
import com.ian.app.muviepedia.tvshow.TvViewModel
import com.ian.app.muviepedia.tvshow.databinding.FragmentTvDetailBinding
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.TvRemoteDetailPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.mapToLocalData
import com.ian.app.muvipedia.presentation.model.tvshow.mapToPresentation
import com.ian.app.muvipedia.presentation.util.MovieDetailConstant.imageFormatter
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import kotlinx.android.synthetic.main.item_similar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvDetailFragment : Fragment() {
    private val tvshowVm: TvViewModel by viewModel()
    private var idForDeleteItem: Int? = null
    private var isFavorite: Boolean = false
    private val requestExternalStorage = 3
    private val permissionStorage = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //dont use this, but i had to
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())


        val permission = ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity!!,
                    permissionStorage,
                    requestExternalStorage
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args = TvDetailFragmentArgs.fromBundle(arguments!!)
        val binding: FragmentTvDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_detail, container, false)
        binding.apply {
            consumeDetailData(args.tvDetailId, this)
            consumeSimilarData(args.tvDetailId, this)

            invalidateAll()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun consumeDetailData(movieId: Int, binding: FragmentTvDetailBinding) {
        binding.apply {
            tvshowVm.observeDetailData(movieId).observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.LOADING -> {
                        progressDetail.visible()
                    }
                    ResultToConsume.Status.SUCCESS -> {
                        progressDetail.gone()
                        val results = result.data?.mapToPresentation()
                        detailData = results
                        inflateView(this@apply, results)
                        consumeSaveDetailData(results?.mapToLocalData()!!, this)

                    }
                  ResultToConsume.Status.ERROR -> {
                        progressDetail.gone()
                        Snackbar.make(nestedParent, result.message!!, Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }


    }

    private fun inflateView(binding: FragmentTvDetailBinding, data: TvRemoteDetailPresentation?) {
        binding.apply {

            ivDetailTvImages.setOnClickListener {
                it.context.fullScreen(data?.poster_path)
            }
            ivShare.setOnClickListener {
                activity?.intentShareImageAndText(tvshowVm.viewModelScope, data?.name!!, data.overview, data.poster_path)
            }
            ivBookmark.setOnClickListener {
                if (data != null)
                    if (isFavorite) {
                        if (idForDeleteItem != null) tvshowVm.deleteSelectedMovie(idForDeleteItem!!)
                    } else {
                        tvshowVm.saveDetailMovieData(data.mapToLocalData())
                    }
            }
            ivDownload.setOnClickListener {
                activity?.saveImage(tvshowVm.viewModelScope,nestedParent,data?.poster_path)
            }
        }
    }

    private fun consumeSimilarData(movieId: Int, binding: FragmentTvDetailBinding) {
        binding.apply {
            tvshowVm.observeSimilarData(movieId).observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.LOADING -> {
                        shimmerSimilar.startShimmer()
                    }
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data?.isNullOrEmpty()!!) {
                            rvSimilarTv.setUpVerticalListAdapter(result.data?.mapToPresentation(), tvAdapterCallback, R.layout.item_similar, {
                                ivSimilarMovie.loadWithGlide( it.poster_path)
                                tvSimilarMovieTittle.text = it.name
                                tvSimilarMovieReleaseDate.text = it.first_air_date
                            }, {
                                if (id != null) {
                                    consumeDetailData(id!!, this@apply)
                                    consumeSimilarData(id!!, this@apply)
                                }
                            })

                            if (shimmerSimilar.isShimmerStarted && shimmerSimilar.isShimmerVisible) {
                                shimmerSimilar.stopShimmer()
                                shimmerSimilar.hideShimmer()
                                shimmerSimilar.gone()
                            }
                        } else {
                            tvNoData.visible()
                            if (shimmerSimilar.isShimmerStarted && shimmerSimilar.isShimmerVisible) {
                                shimmerSimilar.stopShimmer()
                                shimmerSimilar.hideShimmer()
                                shimmerSimilar.gone()
                            }
                        }
                    }
                    com.ian.app.muviepedia.model.ResultToConsume.Status.ERROR -> {
                        if (shimmerSimilar.isShimmerStarted && shimmerSimilar.isShimmerVisible) {
                            shimmerSimilar.stopShimmer()
                            shimmerSimilar.hideShimmer()
                            shimmerSimilar.gone()
                        }

                    }
                }
            })
        }
    }

    private fun consumeSaveDetailData(detailState: TvLocalSaveDetailPresentation, binding: FragmentTvDetailBinding) {
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
