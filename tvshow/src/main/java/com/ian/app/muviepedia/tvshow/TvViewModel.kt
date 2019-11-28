package com.ian.app.muviepedia.tvshow

import androidx.lifecycle.MutableLiveData
import com.ian.app.muviepedia.usecase.TvShowUseCase
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.mapToData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TvViewModel(private val repository: TvShowUseCase) : com.ian.app.muvipedia.presentation.base.BaseViewModel() {
    val mutableEditText: MutableLiveData<String> = MutableLiveData()

    val consumeSaveDetailTv by lazy { repository.getAllSingleDetailTv() }

    val airingToday by lazy { repository.getCacheAiringTodayTv() }

    val popularTv by lazy { repository.getCachePopularTv() }

    val topRated by lazy { repository.getCacheTopRatedTv() }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun observeSearchData(querry: String) = repository.getCacheSearchTv(querry)

    fun saveDetailMovieData(data: TvLocalSaveDetailPresentation) {
        vmScopes.launch { repository.setCacheDetailTv(data.mapToData()) }
    }

    fun deleteSelectedMovie(selectedId: Int) {
        vmScopes.launch { repository.deleteSelectedDetailCache(selectedId) }
    }

    fun loadAllTvShowDataById(selectedId: Int) = repository.getCacheSingleDetailTv(selectedId)


    fun deleteAllMovieData() {
        vmScopes.launch { repository.deleteAllDetailCache() }
    }

    fun observeDetailData(movieID: Int) = repository.getRemoteDetailTv(movieID)

    fun observeSimilarData(movieID: Int) = repository.getRemoteSimilarTv(movieID)

    fun observePopularTvPagination() = repository.getCachePaginationPopularTv()

    fun observeTopRatedPagination() = repository.getCachePaginationTopRatedTv()

    init {
        vmScopes.launch { repository.clearSearchTv() }
    }
}