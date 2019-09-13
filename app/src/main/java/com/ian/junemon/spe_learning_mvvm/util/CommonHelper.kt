package com.ian.junemon.spe_learning_mvvm.util

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ian.junemon.spe_learning_mvvm.base.MyKotlinListWithSlideAdapter
import com.ian.junemon.spe_learning_mvvm.base.MySwipeToDelete
import com.ian.junemon.spe_learning_mvvm.model.MovieData


/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
fun <T> RecyclerView.setUpVerticalListAdapterWithSlideLeft(
        items: List<T>?,
        diffUtil: DiffUtil.ItemCallback<T>,
        layoutResId: Int,
        bindHolder: View.(T) -> Unit,
        itemClick: T.() -> Unit = {},
        blocks: (T) -> Unit = {},
        manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): MyKotlinListWithSlideAdapter<T>? {
    return if (items != null) {
        val adapter = MyKotlinListWithSlideAdapter(layoutResId,
                { bindHolder(it) }, diffUtil, blocks, { itemClick() }).apply {
            layoutManager = manager
            adapter = this
        }
        val itemTouchHelper = ItemTouchHelper(MySwipeToDelete(this.context))
        itemTouchHelper.attachToRecyclerView(this)
        adapter.submitList(items)
        return adapter
    } else null

}


val movieAdapterCallback = object : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}
