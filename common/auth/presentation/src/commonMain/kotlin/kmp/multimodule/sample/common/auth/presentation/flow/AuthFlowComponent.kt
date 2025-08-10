package kmp.multimodule.sample.common.auth.presentation.flow

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kmp.multimodule.sample.common.auth.presentation.forgot.ForgotPasswordComponent
import kmp.multimodule.sample.common.auth.presentation.login.LoginComponent
import kmp.multimodule.sample.common.auth.presentation.register.RegisterComponent

interface AuthFlowComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface NavEvent {
        object OpenMainFlow :
            NavEvent
    }

    sealed interface Child {
        class Login(val component: LoginComponent) :
            Child
        class Register(val component: RegisterComponent) :
            Child
        class ForgotPassword(val component: ForgotPasswordComponent) :
            Child
    }
}