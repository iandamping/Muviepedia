package com.ian.junemon.spe_learning_mvvm.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil

class MyKotlinListWithSlideAdapter<T>(layout: Int, private val bindHolder: View.(T) -> Unit,
                                      diffUtil: DiffUtil.ItemCallback<T>,
                                      private val blocks:(T)->Unit ={},
                                      private val itemClicks: T.() -> Unit
) : MyAbstractAdapter<T>(layout, diffUtil, itemClicks) {

    override fun onBindViewHolder(holder: MyAbstractViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.bindHolder(data)
        holder.itemView.setOnClickListener { itemClicks(data) }
        blocks.invoke(data)
    }

}