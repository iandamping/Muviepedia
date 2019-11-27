package com.ian.app.muviepedia.data.cleanarch.datasource.remote

import com.ian.app.muviepedia.data.BuildConfig.movieApiKey
import com.ian.app.muviepedia.data.base.BaseContinuationSource
import com.ian.app.muviepedia.data.cleanarch.data.datasource.MovieRemoteDataSource
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalNowPlayingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalPopularPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalSearchEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.MovieLocalUpComingPaginationEntity
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToDomain
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToNowPlayingMovie
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToPaginationPopularMovie
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToPaginationUpComingMovie
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToPopularMovie
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToSearchMovie
import com.ian.app.muviepedia.data.cleanarch.datasource.model.movie.mapToUpComingMovie
import com.ian.app.muviepedia.model.movie.MovieRemoteData
import com.ian.app.muviepedia.model.movie.MovieRemoteDetailData
import com.ian.app.muviepedia.model.ResultToConsume
import kotlinx.coroutines.CompletableDeferred

/**
 * Created by Ian Damping on 25,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MovieRemoteDataSourceImpl(private val api: ApiInterface) : BaseContinuationSource(), MovieRemoteDataSource {

    override suspend fun getRemotePopularMovie(): ResultToConsume<List<MovieLocalPopularEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieLocalPopularEntity>>>()
        try {
            val firstData = api.getPopularMovieResponse(movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToPopularMovie()
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

    override suspend fun getRemoteNowPlayingMovie(): ResultToConsume<List<MovieLocalNowPlayingEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieLocalNowPlayingEntity>>>()
        try {
            val firstData = api.getNowPlayingMovieResponse(movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToNowPlayingMovie()
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

    override suspend fun getRemoteUpComingMovie(): ResultToConsume<List<MovieLocalUpComingEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieLocalUpComingEntity>>>()
        try {
            val firstData = api.getUpComingMovieResponse(movieApiKey).getResult()
            val firstDataMap = firstData.data?.results?.mapToUpComingMovie()
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

    override suspend fun getRemoteDetailMovie(movieId: Int): ResultToConsume<MovieRemoteDetailData> {
        val results = CompletableDeferred<ResultToConsume<MovieRemoteDetailData>>()
        try {
            val firstData = api.getDetailMovieResponse(movieId, movieApiKey).getResult()
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

    override suspend fun getRemoteSimilarMovie(movieId: Int): ResultToConsume<List<MovieRemoteData>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieRemoteData>>>()
        try {
            val firstData = api.getSimilarMovieResponse(movieId, movieApiKey).getResult()
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

    override suspend fun getRemoteSearchMovie(querry: String): ResultToConsume<List<MovieLocalSearchEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieLocalSearchEntity>>>()
        try {
            val firstData = api.getSearchMovieResponse(movieApiKey, querry).getResult()
            val firstDataMap = firstData.data?.results?.mapToSearchMovie()
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

    override suspend fun getRemotePaginationPopularMovie(page: Int): ResultToConsume<List<MovieLocalPopularPaginationEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieLocalPopularPaginationEntity>>>()
        try {
            val firstData = api.pagingGetPopularMovieResponse(movieApiKey, page).getResult()
            val firstDataMap = firstData.data?.results?.mapToPaginationPopularMovie()
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

    override suspend fun getRemotePaginationUpComingMovie(page: Int): ResultToConsume<List<MovieLocalUpComingPaginationEntity>> {
        val results = CompletableDeferred<ResultToConsume<List<MovieLocalUpComingPaginationEntity>>>()
        try {
            val firstData = api.pagingGetUpComingMovieResponse(movieApiKey, page).getResult()
            val firstDataMap = firstData.data?.results?.mapToPaginationUpComingMovie()
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