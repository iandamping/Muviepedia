package com.ian.junemon.spe_learning_mvvm.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ian.junemon.spe_learning_mvvm.base.MyKotlinAdapter

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */
inline fun <reified T> T.logE(msg: String?) {
    val tag = T::class.java.simpleName
    Log.e(tag, msg)
}

fun ViewGroup.inflates(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun <T> RecyclerView.setUpVertical(
    items: List<T>?,
    diffUtil: DiffUtil.ItemCallback<T>,
    layoutResId: Int,
    bindHolder: View.(T) -> Unit,
    itemClick: T.() -> Unit = {},
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): MyKotlinAdapter<T>? {
    return if (items != null) {
        val adapter = MyKotlinAdapter(layoutResId, { bindHolder(it) }, diffUtil, { itemClick() }).apply {
            layoutManager = manager
            adapter = this
        }
        adapter.submitList(items)
        return adapter
    } else null

}