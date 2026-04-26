package pl.nepapp.rasoth.core.feature

import org.orbitmvi.orbit.syntax.IntentContext

/**
 * This will handle the [Async] state during an asynchronous call.
 * It will emit proper loading/success/error stated depenending on the asynchronous call step.
 */
interface AsyncContext<STATE : Any, SIDE_EFFECT : Any, RESOURCE : Any> {
    suspend fun execute(
        cachedValue: Async<RESOURCE>? = null,
        reducer: IntentContext<STATE>.(Async<RESOURCE>) -> STATE,
    )

    fun handleError(errorHandler: suspend (Throwable) -> Unit): AsyncContext<STATE, SIDE_EFFECT, RESOURCE>
}

interface AsyncContextFlow<STATE : Any, SIDE_EFFECT : Any, RESOURCE : Any> {
    suspend fun execute(
        reducer: IntentContext<STATE>.(Async<RESOURCE>) -> STATE,
    )

    fun handleError(errorHandler: suspend (Throwable) -> Unit): AsyncContextFlow<STATE, SIDE_EFFECT, RESOURCE>
}