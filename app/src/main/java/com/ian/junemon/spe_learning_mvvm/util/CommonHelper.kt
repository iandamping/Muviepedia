package com.ian.junemon.spe_learning_mvvm.util

import android.util.Log

/**
 *
Created by Ian Damping on 06/07/2019.
Github = https://github.com/iandamping
 */inline fun <reified T> T.logE(msg: String?) {
    val tag = T::class.java.simpleName
    Log.e(tag, msg)
}