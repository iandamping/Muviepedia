package com.ian.app.muviepedia.data.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.ian.app.muviepedia.data.BuildConfig.baseUrl
import com.ian.app.muviepedia.data.MovieDatabase
import com.ian.app.muviepedia.data.api.ApiInterface
import com.ian.app.muviepedia.data.data_source.movie.data.local.MovieLocalDataSource
import com.ian.app.muviepedia.data.data_source.movie.data.remote.MovieRemoteDataSource
import com.ian.app.muviepedia.data.data_source.movie.data.remote.MovieRemoteRepository
import com.ian.app.muviepedia.data.data_source.movie.data.ui.MovieDataViewModel
import com.ian.app.muviepedia.data.data_source.tv.data.local.TvLocalDataSource
import com.ian.app.muviepedia.data.data_source.tv.data.remote.TvRemoteDataSource
import com.ian.app.muviepedia.data.data_source.tv.data.remote.TvRemoteRepository
import com.ian.app.muviepedia.data.data_source.tv.data.ui.TvDataViewModel
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */

fun injectData() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
            listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    dataSourceModule,
                    viewModelModule)
    )
}

val databaseModule = module {
    // Room Database instance
    single {
        Room.databaseBuilder(get(), MovieDatabase::class.java, "LocalMovieDatabase").fallbackToDestructiveMigration()
                .build()
    }
    // localDao instance (get instance from MovieDatabase)
    single { get<MovieDatabase>().movieNowPlayingDao() }
    single { get<MovieDatabase>().moviePopularDao() }
    single { get<MovieDatabase>().movieUpComingDao() }
    single { get<MovieDatabase>().movieUpComingPaginationDao() }
    single { get<MovieDatabase>().moviePopularPaginationDao() }
    single { get<MovieDatabase>().movieSearchDao() }
    single { get<MovieDatabase>().movieSaveDetailDao() }

    single { get<MovieDatabase>().tvAiringTodayDao() }
    single { get<MovieDatabase>().tvPopularDao() }
    single { get<MovieDatabase>().tvPopularPaginationDao() }
    single { get<MovieDatabase>().tvTopRatedDao() }
    single { get<MovieDatabase>().tvTopRatedPaginationDao() }
    single { get<MovieDatabase>().tvSearchDao() }
    single { get<MovieDatabase>().tvSaveDetailDao() }

}

val dataSourceModule = module {
    single { MovieRemoteDataSource(get()) }
    single { MovieLocalDataSource(get()) }
    single { TvRemoteDataSource(get()) }
    single { TvLocalDataSource(get()) }
}

val networkModule = module {
    single { createOkHttpClient() }
    single { createClient<ApiInterface>(get()) }
}

val repositoryModule = module {
    single { MovieRemoteRepository(get(), get()) }
    single { TvRemoteRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieDataViewModel(get()) }
    viewModel { TvDataViewModel(get()) }
}

private fun createOkHttpClient(): OkHttpClient {

    val timeOut = 60L
    val dispatcher = Dispatcher().apply {
        maxRequests = 20
        maxRequestsPerHost = 20
    }

    val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val ongoing = chain.request().newBuilder()
//                ongoing.addHeader(ctx.resources.getString(R.string.retrofit_header1), ctx.resources.getString(R.string.fcm_key))
//                ongoing.addHeader(ctx.resources.getString(R.string.retrofit_header2), ctx.resources.getString(R.string.retrofit_value_header2))
                chain.proceed(ongoing.build())
            }
    return okHttpBuilder.build()
}

private inline fun <reified T> createClient(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(baseUrl)
            .build()
    return retrofit.create(T::class.java)
}