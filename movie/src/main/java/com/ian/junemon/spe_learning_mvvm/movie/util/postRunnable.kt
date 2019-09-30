package com.ian.junemon.spe_learning_mvvm.movie.util

import android.os.Handler
import com.ian.junemon.spe_learning_mvvm.data.util.TvShowDetailConstant.tvDelayMillis

fun Handler.postRunnable(runnable:Runnable) = this.postDelayed(runnable, tvDelayMillis)