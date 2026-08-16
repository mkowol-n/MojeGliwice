package pl.nepapp.rasoth.core.feature

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import org.orbitmvi.orbit.syntax.IntentContext
import org.orbitmvi.orbit.syntax.Syntax

/**
 * @see [AsyncContext]
 */
internal class AsyncContextImpl<STATE : Any, SIDE_EFFECT : Any, RESOURCE : Any>(
    private val action: suspend (STATE) -> RESOURCE,
    private val simpleSyntaxContext: Syntax<STATE, SIDE_EFFECT>,
) : AsyncContext<STATE, SIDE_EFFECT, RESOURCE> {
    private var customErrorHandler: (suspend (Throwable) -> Boolean)? = null
    private var httpErrorHandler: (suspend (code: Int, message: String?) -> Boolean)? = null

    override suspend fun execute(
        cachedValue: Async<RESOURCE>?,
        reducer: IntentContext<STATE>.(Async<RESOURCE>) -> STATE,
    ) {
        try {
            simpleSyntaxContext.reduce { reducer(Loading(cachedValue?.invoke())) }
            val result = action(simpleSyntaxContext.state)
            simpleSyntaxContext.reduce { reducer(Success(result)) }
        } catch (ex: Throwable) {
            currentCoroutineContext().ensureActive()
            customErrorHandler?.invoke(ex)
            simpleSyntaxContext.reduce { reducer(Fail(ex, cachedValue?.invoke())) }
        }
    }

    override fun handleError(errorHandler: suspend (Throwable) -> Boolean): AsyncContext<STATE, SIDE_EFFECT, RESOURCE> {
        customErrorHandler = errorHandler
        return this
    }

    override fun handleHttpError(httpErrorHandler: suspend (code: Int, message: String?) -> Boolean): AsyncContext<STATE, SIDE_EFFECT, RESOURCE> {
        this.httpErrorHandler = httpErrorHandler
        return this
    }
}

/**
 * Creates an [AsyncContext] instance that will handle all [Async] flow state for you
 *
 * @see [AsyncContext]
 */
fun <STATE : Any, SIDE_EFFECT : Any, RESOURCE : Any> Syntax<STATE, SIDE_EFFECT>.async(action: suspend (STATE) -> RESOURCE): AsyncContext<STATE, SIDE_EFFECT, RESOURCE> {
    return AsyncContextImpl(action, this)
}