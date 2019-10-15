package com.ian.app.muviepedia.data.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.app.muviepedia.data.data_source.movie.data.local.model.*
import com.ian.app.muviepedia.data.data_source.movie.data.remote.MovieData

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */
object MovieDetailConstant {

    const val times: Int = Int.MAX_VALUE

    const val upcomingMovie: String = "upcoming"
    const val popularMovie: String = "popular"

    val movieAdapterCallback = object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }
    }


    val localMoviePopularAdapterCallback = object : DiffUtil.ItemCallback<MoviePopularLocalData>() {
        override fun areItemsTheSame(oldItem: MoviePopularLocalData, newItem: MoviePopularLocalData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviePopularLocalData, newItem: MoviePopularLocalData): Boolean {
            return oldItem == newItem
        }
    }

    val localMovieUpComingAdapterCallback = object : DiffUtil.ItemCallback<MovieUpComingLocalData>() {
        override fun areItemsTheSame(oldItem: MovieUpComingLocalData, newItem: MovieUpComingLocalData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUpComingLocalData, newItem: MovieUpComingLocalData): Boolean {
            return oldItem == newItem
        }
    }

    val localMoviePopularPaginationAdapterCallback = object : DiffUtil.ItemCallback<MoviePopularPaginationData>() {
        override fun areItemsTheSame(oldItem: MoviePopularPaginationData, newItem: MoviePopularPaginationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviePopularPaginationData, newItem: MoviePopularPaginationData): Boolean {
            return oldItem == newItem
        }
    }

    val localMovieUpComingPaginationAdapterCallback = object : DiffUtil.ItemCallback<MovieUpComingPaginationData>() {
        override fun areItemsTheSame(oldItem: MovieUpComingPaginationData, newItem: MovieUpComingPaginationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUpComingPaginationData, newItem: MovieUpComingPaginationData): Boolean {
            return oldItem == newItem
        }
    }

    val searchMovieAdapterCallback = object : DiffUtil.ItemCallback<MovieSearchLocalData>() {
        override fun areItemsTheSame(oldItem: MovieSearchLocalData, newItem: MovieSearchLocalData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieSearchLocalData, newItem: MovieSearchLocalData): Boolean {
            return oldItem == newItem
        }
    }

    val savedMovieAdapterCallback = object : DiffUtil.ItemCallback<MovieSaveDetailData>() {
        override fun areItemsTheSame(oldItem: MovieSaveDetailData, newItem: MovieSaveDetailData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieSaveDetailData, newItem: MovieSaveDetailData): Boolean {
            return oldItem == newItem
        }
    }
}