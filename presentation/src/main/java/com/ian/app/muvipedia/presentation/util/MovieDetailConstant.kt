package com.ian.app.muvipedia.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.app.muviepedia.model.movie.MovieLocalPopularPaginationData
import com.ian.app.muviepedia.model.movie.MovieLocalUpComingPaginationData
import com.ian.app.muvipedia.presentation.model.movie.MovieLocalPopularPresentation
import com.ian.app.muvipedia.presentation.model.movie.MovieLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.movie.MovieLocalSearchPresentation
import com.ian.app.muvipedia.presentation.model.movie.MovieLocalUpcomingPresentation
import com.ian.app.muvipedia.presentation.model.movie.MovieRemotePresentation

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */
object MovieDetailConstant {
    const val movieDelayMillis = 4000L
    const val times: Int = Int.MAX_VALUE
    const val imageFormatter = "http://image.tmdb.org/t/p/w500"

    const val upcomingMovie: String = "upcoming"
    const val popularMovie: String = "popular"

    val movieAdapterCallback = object : DiffUtil.ItemCallback<MovieRemotePresentation>() {
        override fun areItemsTheSame(oldItem: MovieRemotePresentation, newItem: MovieRemotePresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieRemotePresentation, newItem: MovieRemotePresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localMoviePopularAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalPopularPresentation>() {
        override fun areItemsTheSame(oldItem: MovieLocalPopularPresentation, newItem: MovieLocalPopularPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalPopularPresentation, newItem: MovieLocalPopularPresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localMovieUpComingAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalUpcomingPresentation>() {
        override fun areItemsTheSame(oldItem: MovieLocalUpcomingPresentation, newItem: MovieLocalUpcomingPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalUpcomingPresentation, newItem: MovieLocalUpcomingPresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localMoviePopularPaginationAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalPopularPaginationData>() {
        override fun areItemsTheSame(oldItem: MovieLocalPopularPaginationData, newItem: MovieLocalPopularPaginationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalPopularPaginationData, newItem: MovieLocalPopularPaginationData): Boolean {
            return oldItem == newItem
        }
    }

    val localMovieUpComingPaginationAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalUpComingPaginationData>() {
        override fun areItemsTheSame(oldItem: MovieLocalUpComingPaginationData, newItem: MovieLocalUpComingPaginationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalUpComingPaginationData, newItem: MovieLocalUpComingPaginationData): Boolean {
            return oldItem == newItem
        }
    }

    val searchMovieAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalSearchPresentation>() {
        override fun areItemsTheSame(oldItem: MovieLocalSearchPresentation, newItem: MovieLocalSearchPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalSearchPresentation, newItem: MovieLocalSearchPresentation): Boolean {
            return oldItem == newItem
        }
    }

    val savedMovieAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalSaveDetailPresentation>() {
        override fun areItemsTheSame(oldItem: MovieLocalSaveDetailPresentation, newItem: MovieLocalSaveDetailPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalSaveDetailPresentation, newItem: MovieLocalSaveDetailPresentation): Boolean {
            return oldItem == newItem
        }
    }
}