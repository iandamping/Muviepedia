package com.ian.app.muviepedia.data.cleanarch.data.repository.pagination.tvshow

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedPaginationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvPaginationTopRatedRepository(
    private val remoteDataSource: TvRemoteDataSource,
    private val localDataSource: TvCacheDataSource
) : PageKeyedDataSource<Int, TvLocalTopRatedPaginationEntity>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvLocalTopRatedPaginationEntity>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, TvLocalTopRatedPaginationEntity>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, TvLocalTopRatedPaginationEntity>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<TvLocalTopRatedPaginationEntity>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val firstData = remoteDataSource.getRemotePaginationTopRatedTv(page)
                checkNotNull(firstData.data) { " ${firstData.message} " }
                assert(firstData.data!!.isNotEmpty())
                localDataSource.setCachePaginationTopRatedTv(firstData.data!!)
                callback(firstData.data!!)
            } catch (e: Exception) {
                logE(e.message!!)
            }
        }
    }
}