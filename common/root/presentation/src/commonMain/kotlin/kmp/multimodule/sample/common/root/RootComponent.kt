package kmp.multimodule.sample.common.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kmp.multimodule.sample.common.auth.presentation.flow.AuthFlowComponent
import kmp.multimodule.sample.common.main.presentation.flow.MainFlowComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class AuthModule(val component: AuthFlowComponent) : Child
        class MainModule(val component: MainFlowComponent) : Child
    }
}