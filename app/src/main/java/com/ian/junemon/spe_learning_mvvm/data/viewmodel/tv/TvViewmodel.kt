package com.ian.junemon.spe_learning_mvvm.data.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ian.junemon.spe_learning_mvvm.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.data.repo.tv.TvRepository
import com.ian.junemon.spe_learning_mvvm.model.TvData
import com.ian.junemon.spe_learning_mvvm.util.computePairResult
import com.ian.junemon.spe_learning_mvvm.util.computeResult
import com.ian.junemon.spe_learning_mvvm.util.retryIO
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class TvViewmodel(private val repo: TvRepository) : BaseViewModel() {

    init {
        viewModelScope.launch {
            retryIO {
                getConcurrentData()
                getPopularMovieAsync()
            }
        }
    }

    /*Backer style*/
    private val _multipledata = MutableLiveData<Pair<Pair<List<TvData>, List<TvData>>, List<TvData>>>()
    private val _multiplePaireddata = MutableLiveData<Pair<List<TvData>, List<TvData>>>()
    private val _tvData = MutableLiveData<List<TvData>>()

    val multipleData: LiveData<Pair<Pair<List<TvData>, List<TvData>>, List<TvData>>>
        get() = _multipledata

    val multiplePairredData:LiveData<Pair<List<TvData>, List<TvData>>>
        get() = _multiplePaireddata

    val tvData: LiveData<List<TvData>>
        get() = _tvData


    /*Coroutine way to get concurrent call*/
    private suspend fun getConcurrentData() {
        val work1 = repo.getAiringTodayTvAsync()
        val work2 = repo.getOnAirTvAsync()
        val work3 = repo.getPopularTvAsync()
        _multipledata.value = computeResult(work1.await().results, work2.await().results, work3.await().results)
    }

    private suspend fun getPopularMovieAsync() {
        val work1 = repo.getPopularTvAsync()
        val work2 = repo.getOnAirTvAsync()
        _multiplePaireddata.value = computePairResult(work1.await().results, work2.await().results)

    }


}