package com.ian.junemon.spe_learning_mvvm.util

import android.util.Log
import com.ian.junemon.spe_learning_mvvm.BuildConfig
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.factor
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.initialDelay
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.maxDelay
import com.ian.junemon.spe_learning_mvvm.util.MovieConstant.times
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import java.io.IOException

fun <A, B, C> computeResult(await1: A, await2: B, await3: C): Pair<Pair<A, B>, C> {
    return Pair(Pair(await1, await2), await3)
}

fun <A, B> computePairResult(await1: A, await2: B): Pair<A, B> {
    return Pair(await1, await2)
}


suspend fun <T> retryIOWithReturn(block: suspend () -> T): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) Log.e("Retry Io", e.localizedMessage)
            // you can log an error here and/or make a more finer-grained
            // analysis of the cause to see if retry is needed
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // last attempt
}

suspend fun retryIO(block: suspend () -> Unit) {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) Log.e("Retry Io", e.localizedMessage)
            // you can log an error here and/or make a more finer-grained
            // analysis of the cause to see if retry is needed
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // last attempt
}



