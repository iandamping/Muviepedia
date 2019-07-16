package com.ian.junemon.spe_learning_mvvm.data.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ian.app.helper.util.computeDoubleResult
import com.ian.app.helper.util.computeTripleResult
import com.ian.app.helper.util.extractDeferred
import com.ian.junemon.spe_learning_mvvm.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.data.repo.tv.TvRepository
import com.ian.junemon.spe_learning_mvvm.model.TvData
import kotlinx.coroutines.launch

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class TvViewmodel(private val repo: TvRepository) : BaseViewModel() {

    init {
        vmScopes.launch {
            /*retryIO {
                getConcurrentData()
                getPopularMovieAsync()
            }*/
            getSingleData()
        }
    }

    /*Backer style*/
    private val _tripleData = MutableLiveData<Triple<List<TvData>, List<TvData>, List<TvData>>>()
    private val _pairdata = MutableLiveData<Pair<List<TvData>, List<TvData>>>()
    private val _singleData = MutableLiveData<List<TvData>>()

    val tripleData: LiveData<Triple<List<TvData>, List<TvData>, List<TvData>>>
        get() = _tripleData

    val pairData: LiveData<Pair<List<TvData>, List<TvData>>>
        get() = _pairdata

    val singleData: LiveData<List<TvData>>
        get() = _singleData


    /*Coroutine way to get concurrent call*/
    private suspend fun getTripleData() {
        val work1 = repo.getAiringTodayTvAsync()
        val work2 = repo.getOnAirTvAsync()
        val work3 = repo.getPopularTvAsync()
        _tripleData.value = computeTripleResult(work1.await().results, work2.await().results, work3.await().results)
    }

    private suspend fun getDoubleData() {
        val work1 = repo.getPopularTvAsync()
        val work2 = repo.getOnAirTvAsync()
        _pairdata.value = computeDoubleResult(work1.await().results, work2.await().results)

    }

    private fun getSingleData() {
        vmScopes.extractDeferred {
            repo.getPopularTvAsync().apply {
                _singleData.value = this.await().results
            }
        }
    }


}