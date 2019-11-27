package com.ian.app.muviepedia.profile.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.muvipedia.presentation.util.loadDrawableWithGlide
import com.ian.app.muviepedia.profile.R

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */
@BindingAdapter("loadImageHelper")
fun loadImageHelper(view: ImageView, url: String?) {
    if (url != null) {
        view.loadWithGlide(url)
    } else {
        view.loadDrawableWithGlide(R.drawable.ic_muvie_pedia)
    }
}

@BindingAdapter("initBookmark")
fun initBookmarkHelper(view: ImageView, state: Boolean) {
    if (state) {
        view.setBackgroundResource(R.drawable.ic_bookmark)
    } else view.setBackgroundResource(R.drawable.ic_unbookmark)
}