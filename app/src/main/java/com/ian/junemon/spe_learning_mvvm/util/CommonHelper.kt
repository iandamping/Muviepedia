package com.ian.junemon.spe_learning_mvvm.util

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.visible
import com.ian.junemon.spe_learning_mvvm.base.*


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

