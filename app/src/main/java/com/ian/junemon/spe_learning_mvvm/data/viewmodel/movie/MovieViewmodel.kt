package com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.ian.app.helper.util.computeDoubleResult
import com.ian.app.helper.util.computeTripleResult
import com.ian.junemon.spe_learning_mvvm.base.*
import com.ian.junemon.spe_learning_mvvm.data.repo.movie.MovieRepository
import com.ian.junemon.spe_learning_mvvm.model.DetailMovieData
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.system.measureTimeMillis

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class MovieViewmodel(private val repo: MovieRepository) : BaseViewModel() {

    val uiState = ObservableField<UiState>(Loading)
    val mutableEditText: MutableLiveData<String> = MutableLiveData()
    private val _concurentData: MutableLiveData<Triple<List<MovieData>, List<MovieData>, List<MovieData>>> = MutableLiveData()
    private val _concurentDetailData: MutableLiveData<Pair<DetailMovieData, List<MovieData>>> = MutableLiveData()
    val concurentDetailData: LiveData<Pair<DetailMovieData, List<MovieData>>>
        get() = _concurentDetailData
    val concurentData: LiveData<Triple<List<MovieData>, List<MovieData>, List<MovieData>>>
        get() = _concurentData

    //Viewmodel Scope
    fun detailMovie(movieID: Int) {
        vmScopes.launch {
            try {
                val work1 = repo.getDetailMovieAsync(movieID)
                val work2 = repo.getSimilarMovieAsync(movieID).results
                val result = computeDoubleResult(work1, work2)
                if (work2.isEmpty()) {
                    uiState.set(NoData)
                }
                if (work1 != null) {
                    _concurentDetailData.value = result
                    uiState.set(HasData)
                }


            } catch (e: HttpException) {
                uiState.set(OnError(e.message))
            } catch (e: Throwable) {
                uiState.set(OnError(e.message))
            }
        }

    }

    //Livedata Scope
    val workConcurrent = liveData {
        try {
            val work1 = repo.getNowPlayingMovieAsync().results
            val work2 = repo.getPopularMovieAsync().results
            val work3 = repo.getUpComingMovieAsync().results
            val result = computeTripleResult(work1, work2, work3)
            when (result != null) {
                true -> {
                    uiState.set(NoData)
                }
                else -> {
                    uiState.set(HasData)
                    emit(result)
                }
            }
        } catch (e: HttpException) {
            uiState.set(OnError(e.message))
        } catch (e: Throwable) {
            uiState.set(OnError(e.message))
        }
    }

    //Viewmodel Scope
    fun workConcurrents() {
        vmScopes.launch {
            try {
                val work1 = repo.getNowPlayingMovieAsync().results.shuffled()
                val work2 = repo.getPopularMovieAsync().results.shuffled()
                val work3 = repo.getUpComingMovieAsync().results.shuffled()
                val result = computeTripleResult(work1, work2, work3)
                when (result.first.isEmpty() && result.second.isEmpty() && result.third.isEmpty()) {
                    true -> {
                        uiState.set(NoData)
                    }
                    else -> {
                        uiState.set(HasData)
                        _concurentData.value = result
                    }
                }
            } catch (e: HttpException) {
                uiState.set(OnError(e.message))
            } catch (e: Throwable) {
                uiState.set(OnError(e.message))
            }
        }
    }


    private fun searchKeywordFlow(data: String): Flow<String> = flow {
        if (data != "") emit(data)
    }

    private fun resultOfFlow(data: List<MovieData>): Flow<List<MovieData>> = flow {
        emit(data)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun extractFlowData(data: String) = liveData {
        val time = measureTimeMillis {
            searchKeywordFlow(data).debounce(200L).buffer().map { repo.getSearchMovieAsync(it) }.flatMapLatest {
                resultOfFlow(it.results)
            }.collectLatest { stringFlow ->
                emit(stringFlow)
            }
        }
        println("finish in $time ms")

    }

}