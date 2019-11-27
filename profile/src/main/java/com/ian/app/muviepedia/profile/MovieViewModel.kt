package com.ian.app.muviepedia.profile

import com.ian.app.muviepedia.usecase.MovieUseCase
import com.ian.app.muvipedia.presentation.base.BaseViewModel
import com.ian.app.muvipedia.presentation.model.movie.MovieLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.movie.mapToData
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 20/09/2019.
Github = https://github.com/iandamping
 */
class MovieViewModel(private val repository: MovieUseCase) : BaseViewModel() {

    val consumeSaveDetailMovie by lazy { repository.getAllSingleDetailMovie() }

    fun saveDetailMovieData(data: MovieLocalSaveDetailPresentation) {
        vmScopes.launch { repository.setCacheDetailMovie(data.mapToData()) }
    }

    fun getDetailSavedMovieByID(selectedId: Int) = repository.getCacheSingleDetailMovie(selectedId)

    fun deleteSelectedMovie(selectedId: Int) {
        vmScopes.launch { repository.deleteSelectedDetailCache(selectedId) }
    }
}