package com.ian.app.muviepedia.data.cleanarch.datasource.remote

import com.ian.app.muviepedia.data.BuildConfig
import com.ian.app.muviepedia.data.base.BaseContinuationSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.TvRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalAiringTodayEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.TvLocalTopRatedPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToAiringTodayTv
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToDomain
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToPaginationPopularTv
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToPaginationTopRatedTv
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToPopularTv
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToSearchTv
import com.ian.app.muviepedia.data.cleanarch.datasource.model.tv.mapToTopRatedTv
import com.ian.app.muviepedia.model.ResultToConsume
import com.ian.app.muviepedia.model.tvshow.TvRemoteData
import com.ian.app.muviepedia.model.tvshow.TvRemoteDetailData
import kotlinx.coroutines.CompletableDeferred

/**
 * Created by Ian Damping on 26,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class TvRemoteDataSourceImpl(private val api: ApiInterface) : BaseContinuationSource(),
    TvRemoteDataSource {
    override suspend fun getRemoteAiringTodayTv(): ResultToConsume<List<TvLocalAiringTodayEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<TvLocalAiringTodayEntity>>>()
        try {
            val firstData = api.getAiringTodayTvResponse(BuildConfig.movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToAiringTodayTv()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemotePopularTv(): ResultToConsume<List<TvLocalPopularEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<TvLocalPopularEntity>>>()
        try {
            val firstData = api.getAiringTodayTvResponse(BuildConfig.movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToPopularTv()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemoteTopRatedTv(): ResultToConsume<List<TvLocalTopRatedEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<TvLocalTopRatedEntity>>>()
        try {
            val firstData = api.getTopRatedTvResponse(BuildConfig.movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToTopRatedTv()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemoteDetailTv(movieId: Int): ResultToConsume<TvRemoteDetailData> {
        val results = CompletableDeferred<ResultToConsume<TvRemoteDetailData>>()
        try {
            val firstData = api.getDetailTvResponse(movieId, BuildConfig.movieApiKey).getResult()
            val firstDataMap = firstData.data?.mapToDomain()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemoteSimilarTV(movieId: Int): ResultToConsume<List<TvRemoteData>> {
        val results = CompletableDeferred<ResultToConsume<List<TvRemoteData>>>()
        try {
            val firstData = api.getSimilarTvResponse(movieId, BuildConfig.movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToDomain()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemoteSearchTv(querry: String): ResultToConsume<List<TvLocalSearchEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<TvLocalSearchEntity>>>()
        try {
            val firstData = api.getSearchTvResponse(BuildConfig.movieApiKey, querry).getResult()
            val firstDataMap = firstData.data?.results?.mapToSearchTv()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemotePaginationPopularTv(page: Int): ResultToConsume<List<TvLocalPopularPaginationEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<TvLocalPopularPaginationEntity>>>()
        try {
            val firstData =
                api.pagingGetPopularTvResponse(BuildConfig.movieApiKey, page).getResult()
            val firstDataMap = firstData.data?.results?.mapToPaginationPopularTv()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }

    override suspend fun getRemotePaginationTopRatedTv(page: Int): ResultToConsume<List<TvLocalTopRatedPaginationEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<TvLocalTopRatedPaginationEntity>>>()
        try {
            val firstData =
                api.pagingGetTopRatedTvResponse(BuildConfig.movieApiKey, page).getResult()
            val firstDataMap = firstData.data?.results?.mapToPaginationTopRatedTv()
            checkNotNull(firstDataMap) {
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(
                ResultToConsume(
                    firstData.status,
                    firstDataMap,
                    firstData.message
                )
            )
        } catch (e: Exception) {
            results.complete(
                ResultToConsume(
                    ResultToConsume.Status.ERROR,
                    null,
                    e.message
                )
            )
        }
        return results.await()
    }
}