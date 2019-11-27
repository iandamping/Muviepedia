package com.ian.app.muviepedia.data.base

import com.ian.app.muviepedia.model.ResultToConsume
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import kotlin.coroutines.resume

/**
 * Created by Ian Damping on 06,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseContinuationSource {

    protected suspend fun <T> Response<T>.getResult(): ResultToConsume<T> =
        suspendCancellableCoroutine { cancellableContinuation ->
            if (this.isSuccessful) {
                val body = this.body()
                if (body != null) cancellableContinuation.resume(ResultToConsume.success(body))
            } else cancellableContinuation.resume(error(" ${this.code()} ${this.message()}"))

            cancellableContinuation.invokeOnCancellation {
                cancellableContinuation.resume(error(it?.message ?: it.toString()))
            }
        }

    private fun <T> error(message: String): ResultToConsume<T> {
        return ResultToConsume.error("Network call has failed for a following reason: $message")
    }
}
