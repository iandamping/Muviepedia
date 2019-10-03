package com.ian.app.muviepedia.movie.movie.ui


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
import com.ian.app.helper.util.fullScreen
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.visible
import com.ian.app.muviepedia.data.BuildConfig.imageFormatter
import com.ian.app.muviepedia.data.data.ResultToConsume
import com.ian.app.muviepedia.data.data_source.movie.data.remote.DetailMovieData
import com.ian.app.muviepedia.data.data_source.movie.data.remote.toSaveDetail
import com.ian.app.muviepedia.data.data_source.movie.data.ui.MovieDataViewModel
import com.ian.app.muviepedia.data.util.MovieDetailConstant.movieAdapterCallback
import com.ian.app.muviepedia.data.util.intentShareImageAndText
import com.ian.app.muviepedia.movie.R
import com.ian.app.muviepedia.movie.databinding.FragmentMovieDetailBinding
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import kotlinx.android.synthetic.main.item_similar.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment() {
    private val vm: MovieDataViewModel by viewModel()
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
        val args = MovieDetailFragmentArgs.fromBundle(arguments!!)
        val binding: FragmentMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        binding.apply {
            consumeDetailData(args.movieID, this)
            consumeSimilarData(args.movieID, this)
            invalidateAll()

        }
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun consumeDetailData(movieId: Int, binding: FragmentMovieDetailBinding) {
        binding.apply {

            vm.observeDetailData(movieId).observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.LOADING -> {
                        progressDetail.visible()
                    }
                    ResultToConsume.Status.SUCCESS -> {
                        progressDetail.gone()
                        result.data?.poster_path = imageFormatter + result.data?.poster_path
                        detailData = result.data
                        inflateView(this@apply, result.data)
                        consumeSaveDetailData(result.data!!, this)
                    }
                    ResultToConsume.Status.ERROR -> {
                        progressDetail.gone()
                        Snackbar.make(coordinatorParent, result.message!!, Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }


    }

    private fun inflateView(binding: FragmentMovieDetailBinding, data: DetailMovieData?) {
        binding.apply {
            ivDetailMovieImages.setOnClickListener {
                it.context.fullScreen(data?.poster_path)
            }
            ivShare.setOnClickListener {
                intentShareImageAndText(vm.viewModelScope, activity, data?.title!!, data.overview, data.poster_path)
            }
            ivBookmark.setOnClickListener {
                if (data != null)
                    if (isFavorite) {
                        if (idForDeleteItem != null) vm.deleteSelectedMovie(idForDeleteItem!!)
                    } else {
                        vm.saveDetailMovieData(data.toSaveDetail())
                    }
            }
        }
    }

    private fun consumeSimilarData(movieId: Int, binding: FragmentMovieDetailBinding) {
        binding.apply {
            vm.observeSimilarData(movieId).observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.LOADING -> {
                        shimmerSimilar.startShimmer()
                    }
                    ResultToConsume.Status.SUCCESS -> {
                        if (!result.data?.results?.isNullOrEmpty()!!) {
                            rvSimilarMovie.setUpVerticalListAdapter(result.data!!.results, movieAdapterCallback, R.layout.item_similar, {
                                ivSimilarMovie.loadWithGlide(imageFormatter + it.poster_path)
                                tvSimilarMovieTittle.text = it.title
                                tvSimilarMovieReleaseDate.text = it.release_date
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
                    ResultToConsume.Status.ERROR -> {
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

    private fun consumeSaveDetailData(detailState: DetailMovieData, binding: FragmentMovieDetailBinding) {
        binding.apply {
            vm.consumeSaveDetailMovie.observe(viewLifecycleOwner, Observer { result ->
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
