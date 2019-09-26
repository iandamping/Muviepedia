package com.ian.junemon.spe_learning_mvvm.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ian.junemon.spe_learning_mvvm.api.ApiInterface
import com.ian.junemon.spe_learning_mvvm.data.MovieDatabase
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalDataSource
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteDataSource
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieRemoteRepository
import com.ian.junemon.spe_learning_mvvm.tv.data.local.TvLocalDataSource
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteDataSource
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteRepository
import kotlinx.coroutines.supervisorScope
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException

/**
 *
Created by Ian Damping on 26/09/2019.
Github = https://github.com/iandamping
 */
class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params),KoinComponent {
    private val api:ApiInterface by inject()
    private val database:MovieDatabase by inject()
    override suspend fun doWork(): Result {
        return try {
            supervisorScope {
                initMovieDataSource(api, database).observeNowPlayingMovie(this)
                initMovieDataSource(api, database).observePopularMovie(this)
                initMovieDataSource(api, database).observeUpComingMovie(this)
                initTvDataSource(api, database).observeAiringToday(this)
                initTvDataSource(api, database).observePopular(this)
                initTvDataSource(api, database).observeTopRated(this)
            }
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }

    }

    private fun initMovieDataSource(api: ApiInterface, db: MovieDatabase): MovieRemoteRepository {
        val localSource = MovieLocalDataSource(db)
        val remoteSource = MovieRemoteDataSource(api)
        return MovieRemoteRepository(remoteSource, localSource)
    }

    private fun initTvDataSource(api: ApiInterface, db: MovieDatabase): TvRemoteRepository {
        val localSource = TvLocalDataSource(db)
        val remoteSource = TvRemoteDataSource(api)
        return TvRemoteRepository(remoteSource, localSource)
    }
}