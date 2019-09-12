package com.ian.junemon.spe_learning_mvvm.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.junemon.spe_learning_mvvm.model.MovieData
import com.ian.junemon.spe_learning_mvvm.model.TvData


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
object MovieConstant {



    const val intentToDetailFromMovie = " intent detail movie"
    const val intentToDetailFromTv = " intent detail tv"
    const val intentToDetailFromSaved = " intent detail Saved"
    const val intentToSearch = " intent Search"
    const val intentSearchTvShow = " intent Search tvShow"
    const val intentSearchMovie = " intent Search Movie"
    const val tvShowDataType = " tv type"
    const val movieDataType = " movie type"
    const val widgetAction = " widget"
    const val widgetMovieExtraItem = " widget Extra item"
    const val alarmPassedItem = " alarm items"
    const val alarmPassedDailyNotificationStatus = " alarm notif items"
    const val alarmPassedNewlyReleaseNotificationStatus = " alarm notif newly release items"
    const val notifName = "Movie Notification"
    const val notifChannelName = "Movies"
    const val notifPendingIntentID = 3490
    const val notifReminderID = 23


    const val times: Int = Int.MAX_VALUE
    const val initialDelay: Long = 100 // 0.1 second
    const val maxDelay: Long = 1000    // 1 second
    const val factor: Double = 2.0


    val movieDiffCallbacks = object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }

    }

    val tvDiffCallbacks = object : DiffUtil.ItemCallback<TvData>() {
        override fun areItemsTheSame(oldItem: TvData, newItem: TvData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvData, newItem: TvData): Boolean {
            return oldItem == newItem
        }

    }


}