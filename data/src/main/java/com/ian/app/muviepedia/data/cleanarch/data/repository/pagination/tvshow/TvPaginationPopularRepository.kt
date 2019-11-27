package com.ian.app.muviepedia.data.cleanarch.data.repository.pagination.tvshow

import androidx.paging.PageKeyedDataSource
import com.ian.app.helper.util.logE
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvCacheDataSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularPaginationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Ian Damping on 27,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvPaginationPopularRepository(private val remoteDataSource: TvRemoteDataSource, private val localDataSource: TvCacheDataSource, private val scope: CoroutineScope) : PageKeyedDataSource<Int, TvLocalPopularPaginationEntity>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvLocalPopularPaginationEntity>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, TvLocalPopularPaginationEntity>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, TvLocalPopularPaginationEntity>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<TvLocalPopularPaginationEntity>) -> Unit) {
        scope.launch {
            try {
                val firstData = remoteDataSource.getRemotePaginationPopularTv(page)
                checkNotNull(firstData.data) { " ${firstData.message} " }
                assert(firstData.data!!.isNotEmpty())
                localDataSource.setCachePaginationPopularTv(firstData.data!!)
                callback(firstData.data!!)
            } catch (e: Exception) {
                logE(e.message!!)
            }
        }
    }
}