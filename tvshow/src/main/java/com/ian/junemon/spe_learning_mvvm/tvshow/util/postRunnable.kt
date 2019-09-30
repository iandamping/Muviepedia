package com.ian.junemon.spe_learning_mvvm.tvshow.util

import android.os.Handler
import com.ian.junemon.spe_learning_mvvm.data.util.TvShowDetailConstant.tvDelayMillis

fun Handler.postRunnable(runnable:Runnable) = this.postDelayed(runnable, tvDelayMillis)