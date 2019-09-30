package com.ian.junemon.spe_learning_mvvm.tvshow.tv.ui

import androidx.lifecycle.MutableLiveData
import com.ian.junemon.spe_learning_mvvm.data.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.local.model.TvSaveDetailData
import com.ian.junemon.spe_learning_mvvm.data.data_source.tv.data.remote.TvRemoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TvDataViewModel(private val repository: TvRemoteRepository): BaseViewModel() {
    val mutableEditText: MutableLiveData<String> = MutableLiveData()

    val consumeSaveDetailTv by lazy { repository.getDetailData }

    val airingToday by lazy { repository.observeAiringToday(vmScopes) }

    val popularTv by lazy { repository.observePopular(vmScopes) }

    val topRated by lazy { repository.observeTopRated(vmScopes) }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun observeSearchData(querry: String) = repository.observeSearchTv(querry,vmScopes)

    fun saveDetailMovieData(data: TvSaveDetailData){
        vmScopes.launch { repository.saveDetailData(data) }
    }

    fun deleteSelectedMovie(selectedId:Int){
        vmScopes.launch { repository.deleteSelectedDetailData(selectedId) }
    }

    fun observeDetailData(movieID: Int) = repository.getDetailTv(movieID)

    fun observeSimilarData(movieID: Int) = repository.getSimilarTv(movieID)

    fun observePopularTvPagination(connectivityAvailable: Boolean) = repository.observePopularTvPagination(connectivityAvailable, vmScopes)

    fun observeTopRatedPagination(connectivityAvailable: Boolean) = repository.observeTopRatedPagination(connectivityAvailable, vmScopes)

    init {
        vmScopes.launch { repository.clearSearchTvData() }
    }
}