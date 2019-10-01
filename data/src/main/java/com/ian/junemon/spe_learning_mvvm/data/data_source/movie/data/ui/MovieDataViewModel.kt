package com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.ui

import androidx.lifecycle.MutableLiveData
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.local.model.MovieSaveDetailData
import com.ian.junemon.spe_learning_mvvm.data.data_source.movie.data.remote.MovieRemoteRepository
import com.ian.junemon.spe_learning_mvvm.data.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
class MovieDataViewModel(private val repository: MovieRemoteRepository) : BaseViewModel() {

    val movieSearchEditText: MutableLiveData<String> = MutableLiveData()

    val consumeSaveDetailMovie by lazy { repository.getDetailData }

    val nowPlayingMovie by lazy { repository.observeNowPlayingMovie(vmScopes) }

    val popularMovie by lazy { repository.observePopularMovie(vmScopes) }

    val upComingMovie by lazy { repository.observeUpComingMovie(vmScopes) }

    fun saveDetailMovieData(data: MovieSaveDetailData){
        vmScopes.launch { repository.saveDetailData(data) }
    }

    fun deleteSelectedMovie(selectedId:Int){
        vmScopes.launch { repository.deleteSelectedDetailData(selectedId) }
    }

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