package com.ian.junemon.spe_learning_mvvm.movie.ui

import androidx.lifecycle.MutableLiveData
import com.ian.app.helper.model.GenericViewModelZipperTriple
import com.ian.app.helper.util.computeTripleResult
import com.ian.junemon.spe_learning_mvvm.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
class MovieDataViewModel(private val repository: MovieRemoteRepository) : BaseViewModel() {

    val mutableEditText: MutableLiveData<String> = MutableLiveData()

    val nowPlayingMovie by lazy { repository.observeNowPlayingMovie() }

    val popularMovie by lazy { repository.observePopularMovie() }

    val upComingMovie by lazy { repository.observeUpComingMovie() }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun observeSearchData(querry: String) = repository.observeSearchMovie(querry)

    fun observeDetailData(movieID: Int) = repository.getDetailMovie(movieID)

    fun observeSimilarData(movieID: Int) = repository.getSimilarMovie(movieID)

    fun observePagination(connectivityAvailable: Boolean, movieType: String) = repository.observePagination(connectivityAvailable, movieType, vmScopes)
}