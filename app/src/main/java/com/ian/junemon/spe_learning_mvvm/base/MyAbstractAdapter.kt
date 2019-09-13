package com.ian.junemon.spe_learning_mvvm.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ian.app.helper.util.inflates
import kotlinx.android.extensions.LayoutContainer

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */
abstract class MyAbstractAdapter<T>(
        private val layout: Int,
        diffUtil: DiffUtil.ItemCallback<T>,
        private val clickListener: (T) -> Unit
) : ListAdapter<T, MyAbstractAdapter.MyAbstractViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAbstractViewHolder {
        return MyAbstractViewHolder(parent.inflates(layout))
    }

    override fun onBindViewHolder(holder: MyAbstractViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.bind(item)
        holder.itemView.setOnClickListener { clickListener(item) }
    }

    protected open fun View.bind(item: T) {
    }


    class MyAbstractViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer


}



