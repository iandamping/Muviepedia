package com.ian.junemon.spe_learning_mvvm.util


/**
 *
Created by Ian Damping on 24/06/2019.
Github = https://github.com/iandamping
 */
object MovieConstant {


    const val popularPagingState = " intent detail movie"
    const val nowPlayingPagingState = " intent detail tv"
    const val upcomingPagingState = " intent detail Saved"

    const val times: Int = Int.MAX_VALUE


    /* val movieDiffCallbacks = object : DiffUtil.ItemCallback<MovieData>() {
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

     }*/


}