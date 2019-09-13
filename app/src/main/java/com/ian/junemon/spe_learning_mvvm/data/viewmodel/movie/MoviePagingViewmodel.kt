package com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ian.junemon.spe_learning_mvvm.base.BaseViewModel
import com.ian.junemon.spe_learning_mvvm.data.repo.movie.MovieRepository
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.paging.NowPlayingDataSource
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.paging.PopularDataSource
import com.ian.junemon.spe_learning_mvvm.data.viewmodel.movie.paging.UpcomingDataSource
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.nowPlayingPagingState
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.popularPagingState

/**
 *
Created by Ian Damping on 13/09/2019.
Github = https://github.com/iandamping
 */
class MoviePagingViewmodel(private val repo: MovieRepository) : BaseViewModel() {

    private val pageSize = 10
    private val isPlaceHolder = true
    private lateinit var movieList: LiveData<PagedList<MovieData>>

    fun getAllMovies(states: String): LiveData<PagedList<MovieData>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(isPlaceHolder)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(15)
                .build()
        movieList = initPagedListBuilder(config, states).build()
        return movieList
    }

    private fun initPagedListBuilder(config: PagedList.Config, states: String): LivePagedListBuilder<Int, MovieData> {
        val dataSourceFactory = object : DataSource.Factory<Int, MovieData>() {
            override fun create(): DataSource<Int, MovieData> {
                return when (states) {
                    popularPagingState -> PopularDataSource(repo, vmScopes)
                    nowPlayingPagingState -> NowPlayingDataSource(repo, vmScopes)
                    else -> UpcomingDataSource(repo, vmScopes)
                }

            }
        }
        return LivePagedListBuilder<Int, MovieData>(dataSourceFactory, config)
    }
}