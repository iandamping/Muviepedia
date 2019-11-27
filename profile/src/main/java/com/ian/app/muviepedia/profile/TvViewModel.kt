package com.ian.app.muviepedia.profile

import com.ian.app.muviepedia.usecase.TvShowUseCase
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.mapToData
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TvViewModel(private val repository: TvShowUseCase) : com.ian.app.muvipedia.presentation.base.BaseViewModel() {

    val consumeSaveDetailTv by lazy { repository.getAllSingleDetailTv() }

    fun saveDetailMovieData(data: TvLocalSaveDetailPresentation) {
        vmScopes.launch { repository.setCacheDetailTv(data.mapToData()) }
    }

    fun deleteSelectedMovie(selectedId: Int) {
        vmScopes.launch { repository.deleteSelectedDetailCache(selectedId) }
    }

    fun loadAllTvShowDataById(selectedId: Int) = repository.getCacheSingleDetailTv(selectedId)
}