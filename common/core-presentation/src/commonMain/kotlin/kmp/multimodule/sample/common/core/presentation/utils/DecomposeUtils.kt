package kmp.multimodule.sample.common.core.presentation.utils

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

fun <T : Any> Value<T>.tryToUpdate(function: (T) -> T) {
    if (this is MutableValue<T>) {
        value = function.invoke(value)
    }
}

/**
 * Creates a [ChildStack] with a single active component, should be used to create a stack for Jetpack Compose preview
 */
fun <T : Any> createFakeChildStack(instance: T): ChildStack<*, T> {
    return ChildStack(
        configuration = "<fake>",
        instance = instance,
    )
}

fun <T : Any> createFakeChildStackValue(instance: T): Value<ChildStack<*, T>> {
    return MutableValue(createFakeChildStack(instance))
}