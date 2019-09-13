package com.ian.junemon.spe_learning_mvvm.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.visible
import com.ian.junemon.spe_learning_mvvm.base.*

/**
 *
Created by Ian Damping on 11/09/2019.
Github = https://github.com/iandamping
 */

@BindingAdapter("UiState")
fun setUiStateForLoading(progressView: ShimmerFrameLayout, state: UiState) {
    when (state) {
        Loading -> progressView.startShimmer()

        HasData -> {
            if (progressView.isShimmerStarted && progressView.isShimmerVisible) {
                progressView.stopShimmer()
                progressView.hideShimmer()
                progressView.gone()
            }
        }

        NoData -> {
            if (progressView.isShimmerStarted && progressView.isShimmerVisible) {
                progressView.stopShimmer()
                progressView.hideShimmer()
                progressView.gone()
            }
        }
        else -> progressView.gone()
    }
}

@BindingAdapter("UiState")
fun setUiStateForNoData(view: View, state: UiState) {
    when (state) {
        NoData -> view.visible()
        else -> view.gone()
    }
}

@BindingAdapter("UiState")
fun setUiStateForHasData(view: View, state: UiState) {
    when (state) {
        HasData -> view.visible()
        else -> view.gone()
    }
}

@BindingAdapter("UiState")
fun setUiStateForError(view: TextView, state: UiState) {
    when (state) {
        is OnError -> {
            view.visible()
            view.text = state.msg
        }
        else -> view.gone()
    }
}

@BindingAdapter("loadImageHelper")
fun loadImageHelper(view: ImageView, url: String?) {
    if (url != null) view.loadWithGlide(url)
}
