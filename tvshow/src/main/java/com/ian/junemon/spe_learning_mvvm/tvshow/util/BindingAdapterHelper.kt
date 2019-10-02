package com.ian.junemon.spe_learning_mvvm.tvshow.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ian.app.helper.util.loadWithGlide

/**
 *
Created by Ian Damping on 11/09/2019.
Github = https://github.com/iandamping
 */


@BindingAdapter("loadImageHelper")
fun loadImageHelper(view: ImageView, url: String?) {
    if (url != null) view.loadWithGlide(url)
}
