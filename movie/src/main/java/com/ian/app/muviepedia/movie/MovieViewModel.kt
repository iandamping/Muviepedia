package com.ian.app.muviepedia.movie

import androidx.lifecycle.MutableLiveData
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

    val movieSearchEditText: MutableLiveData<String> = MutableLiveData()

    val consumeSaveDetailMovie by lazy { repository.getAllSingleDetailMovie() }

    val nowPlayingMovie by lazy { repository.getCacheNowPlayingMovie() }

    val popularMovie by lazy { repository.getCachePopularMovie() }

    val upComingMovie by lazy { repository.getCacheUpComingMovie() }

    fun saveDetailMovieData(data: MovieLocalSaveDetailPresentation) {
        vmScopes.launch { repository.setCacheDetailMovie(data.mapToData()) }
    }

    fun getDetailSavedMovieByID(selectedId: Int) = repository.getCacheSingleDetailMovie(selectedId)

    fun deleteSelectedMovie(selectedId: Int) {
        vmScopes.launch { repository.deleteSelectedDetailCache(selectedId) }
    }

    fun deleteAllDetailMovieData() {
        vmScopes.launch { repository.deleteAllDetailCache() }
    }

    fun observeSearchData(querry: String) = repository.getCacheSearchMovie(querry)

    fun observeDetailData(movieID: Int) = repository.getRemoteDetailMovie(movieID)

    fun observeSimilarData(movieID: Int) = repository.getRemoteSimilarMovie(movieID)

    fun observePopularPagination() = repository.getCachePaginationPopularMovie(vmScopes)

    fun observeUpComingPagination() = repository.getCachePaginationUpComingMovie(vmScopes)

    init {
        vmScopes.launch { repository.clearSearchMovie() }
    }
}