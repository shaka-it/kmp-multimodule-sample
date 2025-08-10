package kmp.multimodule.sample.common.core.presentation.utils

fun interface Consumer<in T> {
    fun onConsume(value: T)
}

@Suppress("FunctionName")
inline fun <T> Consumer(crossinline block: (T) -> Unit): Consumer<T> =
    object : Consumer<T> {
        override fun onConsume(value: T) {
            block(value)
        }
    }

operator fun <T> Consumer<T>.invoke(value: T) {
    onConsume(value)
}