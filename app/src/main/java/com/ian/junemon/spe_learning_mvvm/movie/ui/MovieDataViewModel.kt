package com.ian.junemon.spe_learning_mvvm.movie.ui

import androidx.lifecycle.MutableLiveData
import com.ian.junemon.spe_learning_mvvm.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
class MovieDataViewModel(private val repository: MovieRemoteRepository) : BaseViewModel() {

    val mutableEditText: MutableLiveData<String> = MutableLiveData()

    val nowPlayingMovie by lazy { repository.observeNowPlayingMovie(vmScopes) }

    val popularMovie by lazy { repository.observePopularMovie(vmScopes) }

    val upComingMovie by lazy { repository.observeUpComingMovie(vmScopes) }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun observeSearchData(querry: String) = repository.observeSearchMovie(querry,vmScopes)

    fun observeDetailData(movieID: Int) = repository.getDetailMovie(movieID)

    fun observeSimilarData(movieID: Int) = repository.getSimilarMovie(movieID)

    fun observePopularPagination(connectivityAvailable: Boolean) = repository.observePopularPagination(connectivityAvailable, vmScopes)

    fun observeUpComingPagination(connectivityAvailable: Boolean) = repository.observeUpComingPagination(connectivityAvailable, vmScopes)

    init {
        vmScopes.launch { repository.clearSearchMovie() }
    }
}