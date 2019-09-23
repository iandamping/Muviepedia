package com.ian.junemon.spe_learning_mvvm.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.junemon.spe_learning_mvvm.movie.data.local.MovieLocalData
import com.ian.junemon.spe_learning_mvvm.movie.data.remote.MovieData


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
object MovieConstant {


    const val times: Int = Int.MAX_VALUE

    const val upcomingMovie: String = "upcoming"
    const val nowPlayingMovie: String = "now playing"
    const val popularMovie: String = "popular"

    val movieAdapterCallback = object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }
    }

    val localMovieAdapterCallback = object : DiffUtil.ItemCallback<MovieLocalData>() {
        override fun areItemsTheSame(oldItem: MovieLocalData, newItem: MovieLocalData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieLocalData, newItem: MovieLocalData): Boolean {
            return oldItem == newItem
        }
    }


}