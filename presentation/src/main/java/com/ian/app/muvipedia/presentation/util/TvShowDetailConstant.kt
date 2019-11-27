package com.ian.app.muvipedia.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.app.muviepedia.model.tvshow.TvLocalPopularPaginationData
import com.ian.app.muviepedia.model.tvshow.TvLocalTopRatedPaginationData
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalPopularPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalSaveDetailPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalSearchPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.TvLocalTopRatedPresentation
import com.ian.app.muvipedia.presentation.model.tvshow.TvRemotePresentation

/**
 *
Created by Ian Damping on 30/09/2019.
Github = https://github.com/iandamping
 */
object TvShowDetailConstant {
    const val popularTv: String = "popularTv"
    const val topRatedTv: String = "top rated tv"
    const val tvDelayMillis = 4000L
    const val imageFormatter = "http://image.tmdb.org/t/p/w500"

    val tvAdapterCallback = object : DiffUtil.ItemCallback<TvRemotePresentation>() {
        override fun areItemsTheSame(oldItem: TvRemotePresentation, newItem: TvRemotePresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvRemotePresentation, newItem: TvRemotePresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localTvPopularAdapterCallback = object : DiffUtil.ItemCallback<TvLocalPopularPresentation>() {
        override fun areItemsTheSame(oldItem: TvLocalPopularPresentation, newItem: TvLocalPopularPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvLocalPopularPresentation, newItem: TvLocalPopularPresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localTopRatedAdapterCallback = object : DiffUtil.ItemCallback<TvLocalTopRatedPresentation>() {
        override fun areItemsTheSame(oldItem: TvLocalTopRatedPresentation, newItem: TvLocalTopRatedPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvLocalTopRatedPresentation, newItem: TvLocalTopRatedPresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localTvSearchAdapterCallback = object : DiffUtil.ItemCallback<TvLocalSearchPresentation>() {
        override fun areItemsTheSame(oldItem: TvLocalSearchPresentation, newItem: TvLocalSearchPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvLocalSearchPresentation, newItem: TvLocalSearchPresentation): Boolean {
            return oldItem == newItem
        }
    }

    val localTvPopularPaginationAdapterCallback = object : DiffUtil.ItemCallback<TvLocalPopularPaginationData>() {
        override fun areItemsTheSame(oldItem: TvLocalPopularPaginationData, newItem: TvLocalPopularPaginationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvLocalPopularPaginationData, newItem: TvLocalPopularPaginationData): Boolean {
            return oldItem == newItem
        }
    }

    val localTvTopRatedPaginationAdapterCallback = object : DiffUtil.ItemCallback<TvLocalTopRatedPaginationData>() {
        override fun areItemsTheSame(oldItem: TvLocalTopRatedPaginationData, newItem: TvLocalTopRatedPaginationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvLocalTopRatedPaginationData, newItem: TvLocalTopRatedPaginationData): Boolean {
            return oldItem == newItem
        }
    }

    val savedTvShowAdapterCallback = object : DiffUtil.ItemCallback<TvLocalSaveDetailPresentation>() {
        override fun areItemsTheSame(oldItem: TvLocalSaveDetailPresentation, newItem: TvLocalSaveDetailPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvLocalSaveDetailPresentation, newItem: TvLocalSaveDetailPresentation): Boolean {
            return oldItem == newItem
        }
    }
}