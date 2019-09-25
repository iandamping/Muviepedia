package com.ian.junemon.spe_learning_mvvm.tv.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ian.junemon.spe_learning_mvvm.databinding.SearchItemMovieBinding
import com.ian.junemon.spe_learning_mvvm.databinding.SearchItemTvBinding
import com.ian.junemon.spe_learning_mvvm.tv.data.remote.TvRemoteData
import com.ian.junemon.spe_learning_mvvm.util.TvConstant

/**
 *
Created by Ian Damping on 24/09/2019.
Github = https://github.com/iandamping
 */
class TvSearchAdapter(private val itemClicks: (TvRemoteData) -> Unit={}): ListAdapter<TvRemoteData, TvSearchAdapter.TvSearchViewHolder>(TvConstant.tvAdapterCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSearchViewHolder {
        return TvSearchViewHolder(SearchItemTvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TvSearchViewHolder, position: Int) {
        val data = getItem(position)
        holder.apply {
            bind(data,itemClicks)
        }
    }


    class TvSearchViewHolder(private val binding: SearchItemTvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:TvRemoteData,listener:(TvRemoteData) -> Unit){
            binding.apply {
                searchData = data
                itemView.setOnClickListener{listener(data)}
            }
        }

    }
}