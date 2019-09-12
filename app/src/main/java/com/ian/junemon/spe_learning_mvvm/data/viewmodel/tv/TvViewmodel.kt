package com.ian.junemon.spe_learning_mvvm.data.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ian.junemon.spe_learning_mvvm.base.*
import com.ian.junemon.spe_learning_mvvm.data.repo.tv.TvRepository

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
class TvViewmodel(private val repo: TvRepository) : BaseViewModel() {

    val uiState: LiveData<UiState>
        get() = baseUiState

    init {
        setBaseUiState(Loading)
    }

    val popularTv = liveData {
        try {
            val data = repo.getPopularTvAsync().results
            when (data.isEmpty()) {
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

    fun detailTvData(tvId: Int?) = liveData {
        if (tvId != null) {
            try {
                val data = repo.getDetailTvAsync(tvId)
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

    }

//    val uiState = ObservableField<UiState>(Loading)

    /*   init {
           createOrders()
           useTheChannelProduce()
           vmScopes.launch {
               retryIO {
                   getConcurrentData()
                   getPopularMovieAsync()
               }
               getSingleListData()
           }
       }*/

    /*Backer style*/
    /*private val _tripleData = MutableLiveData<Triple<List<TvData>, List<TvData>, List<TvData>>>()
    private val _pairdata = MutableLiveData<Pair<List<TvData>, List<TvData>>>()
    private val _singleData = MutableLiveData<List<TvData>>()

    val tripleData: LiveData<Triple<List<TvData>, List<TvData>, List<TvData>>>
        get() = _tripleData

    val pairData: LiveData<Pair<List<TvData>, List<TvData>>>
        get() = _pairdata

    val singleListData: LiveData<List<TvData>>
        get() = _singleData
    val singleListData = liveData(Dispatchers.IO) {
        try {
            val data = repo.getPopularTvAsync().results
            when (data.isEmpty()) {
                true -> {
                    uiState.set(NoData)
                }
                else -> {
                    uiState.set(HasData)
                    emit(data)
                }
            }

        } catch (e: Exception) {
            uiState.set(OnError(e.message))
        }
    }*/

    /* *//*Coroutine way to get concurrent call*//*
    private suspend fun getTripleData() {
        val work1 = repo.getAiringTodayTvAsync()
        val work2 = repo.getOnAirTvAsync()
        val work3 = repo.getPopularTvAsync()
        _tripleData.value = computeTripleResult(work1.await().results, work2.await().results, work3.await().results)
    }
*/
    /*private suspend fun getDoubleData() {
        val work1 = repo.getPopularTvAsync()
        val work2 = repo.getOnAirTvAsync()
        _pairdata.value = computeDoubleResult(work1.await().results, work2.await().results)

    }*/

    /*fun getSingleListData() = liveData(Dispatchers.IO) {
        val data = repo.getPopularTvAsync().results
        emit(data)
    }*/

    /* private fun useTheChannelProduce() {
         vmScopes.launch {
             val response = repo.getPopularTvAsync().await().results
             produce(CoroutineName("Testing produce")) {
                 try {
                     when (response.isEmpty()) {
                         true -> {
                             uiState.set(NoData)
                         }
                         else -> {
                             send(response)
                             uiState.set(HasData)
                         }
                     }
                 } catch (e: Exception) {
                     uiState.set(OnError(e.message))
                 }

             }.also {
                 it.consume {
                     _singleData.value = this.receive()
                 }
             }
         }
     }*/


    /* private val orders = listOf(
         repo.getAiringTodayTvAsync(), repo.getPopularTvAsync(), repo.getOnAirTvAsync()
     )*/


    /*@ExperimentalCoroutinesApi
    private fun createOrders() {
        vmScopes.launch {
            val orderChannels = produce {
                send(orders)
            }.also {
                coroutineScope {
                    launch {
                        it.consumeEach {

                        }
                    }
                }
            }


            val time = measureTimeMillis {
                coroutineScope {
                    launch { consumeOrders(orderChannels) }
                }
                *//*  repeat(orders.size) {
                      val airingToday = orders[0].onAwait
                      val popular = orders[1].onAwait
                      val onair = orders[2].onAwait
                  }*//*
            }
            logE("get data in $time")

        }
    }

    private suspend fun consumeOrders(ordersChannel: ReceiveChannel<List<Deferred<GenericMovieModel<TvData>>>>) {
        ordersChannel.consumeEach {
            val airingToday = it[0].await()
            val popular = it[1].await()
            val onair = it[2].await()
        }
    }*/

}
