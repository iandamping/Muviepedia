package com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.ian.app.helper.model.GenericViewModelWithLiveData
import com.ian.app.helper.model.GenericViewModelZipperPair
import com.ian.app.helper.util.computeTripleResult
import com.ian.junemon.spe_learning_mvvm.base.*
import com.ian.junemon.spe_learning_mvvm.data.repo.movie.MovieRepository
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import com.ian.junemon.spe_learning_mvvm.model.TvData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class MovieViewmodel(private val repo: MovieRepository) : BaseViewModel() {
    val uiState: LiveData<UiState>
        get() = baseUiState

    private val _concurentData:MutableLiveData<Triple<List<MovieData>,List<MovieData>,List<MovieData>>> = MutableLiveData()
    val concurentData:LiveData<Triple<List<MovieData>,List<MovieData>,List<MovieData>>>
    get() = _concurentData

    init {
        setBaseUiState(Loading)
        workConcurrents()
    }


    fun detailMovie(movieID: Int) = liveData {
        try {
            val data = repo.getDetailMovieAsync(movieID)
            when (data != null) {
                true -> {
                    setBaseUiState(NoData)
                }
                else -> {
                    setBaseUiState(HasData)
                    emit(data)
                }
            }

        } catch (e: Exception) {
            setBaseUiState(OnError(e.message))
        }
    }

    //Livedata Scope
    val workConcurrent = liveData {
        try {
            val work1 =   repo.getNowPlayingMovieAsync().results
            val work2 =   repo.getPopularMovieAsync().results
            val work3 =   repo.getUpComingMovieAsync().results
            val result = computeTripleResult(work1, work2, work3)
            when (result != null) {
                true -> {
                    setBaseUiState(NoData)
                }
                else -> {
                    setBaseUiState(HasData)
                    emit(result)
                }
            }
        } catch (e: HttpException) {
            setBaseUiState(OnError(e.message))
        } catch (e: Throwable) {
            setBaseUiState(OnError(e.message))
        }
    }

    //Viewmodel Scope
    private fun workConcurrents() {
        vmScopes.launch {
            try {
                val work1 =   repo.getNowPlayingMovieAsync().results.shuffled()
                val work2 =   repo.getPopularMovieAsync().results.shuffled()
                val work3 =   repo.getUpComingMovieAsync().results.shuffled()
                val result = computeTripleResult(work1, work2, work3)
                when (result.first.isEmpty() && result.second.isEmpty() && result.third.isEmpty() ) {
                    true -> {
                        setBaseUiState(NoData)
                    }
                    else -> {
                        setBaseUiState(HasData)
                        _concurentData.value = result
                    }
                }
            } catch (e: HttpException) {
                setBaseUiState(OnError(e.message))
            } catch (e: Throwable) {
                setBaseUiState(OnError(e.message))
            }
        }
    }



}