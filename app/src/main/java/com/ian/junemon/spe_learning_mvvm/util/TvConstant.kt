package com.ian.junemon.spe_learning_mvvm.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvSearchLocalData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvPopularData
import com.ian.junemon.spe_learning_mvvm.tv.data.local.model.TvTopRatedData
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteData

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
object TvConstant {

    val tvAdapterCallback = object : DiffUtil.ItemCallback<TvRemoteData>() {
        override fun areItemsTheSame(oldItem: TvRemoteData, newItem: TvRemoteData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvRemoteData, newItem: TvRemoteData): Boolean {
            return oldItem == newItem
        }
    }

    val localTvPopularAdapterCallback = object : DiffUtil.ItemCallback<TvPopularData>() {
        override fun areItemsTheSame(oldItem: TvPopularData, newItem: TvPopularData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvPopularData, newItem: TvPopularData): Boolean {
            return oldItem == newItem
        }
    }

    val localTopRatedAdapterCallback = object : DiffUtil.ItemCallback<TvTopRatedData>() {
        override fun areItemsTheSame(oldItem: TvTopRatedData, newItem: TvTopRatedData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvTopRatedData, newItem: TvTopRatedData): Boolean {
            return oldItem == newItem
        }
    }

    val tvSearchAdapterCallback = object : DiffUtil.ItemCallback<TvSearchLocalData>() {
        override fun areItemsTheSame(oldItem: TvSearchLocalData, newItem: TvSearchLocalData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSearchLocalData, newItem: TvSearchLocalData): Boolean {
            return oldItem == newItem
        }
    }

}