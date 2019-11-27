package com.ian.app.muviepedia.tvshow.util

import android.os.Handler
import com.ian.app.muvipedia.presentation.util.TvShowDetailConstant.tvDelayMillis

fun Handler.postRunnable(runnable: Runnable) = this.postDelayed(runnable, tvDelayMillis)