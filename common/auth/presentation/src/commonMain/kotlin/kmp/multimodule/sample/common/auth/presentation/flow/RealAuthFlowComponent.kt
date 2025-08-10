package kmp.multimodule.sample.common.auth.presentation.flow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kmp.multimodule.sample.common.auth.presentation.createForgotPasswordComponent
import kmp.multimodule.sample.common.auth.presentation.createLoginComponent
import kmp.multimodule.sample.common.auth.presentation.createRegisterComponent
import kmp.multimodule.sample.common.auth.presentation.forgot.ForgotPasswordComponent
import kmp.multimodule.sample.common.auth.presentation.login.LoginComponent
import kmp.multimodule.sample.common.auth.presentation.register.RegisterComponent
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.core.presentation.utils.invoke
import kotlinx.serialization.Serializable

class RealAuthFlowComponent(
    componentContext: ComponentContext,
    private val onNavEvent: Consumer<AuthFlowComponent.NavEvent>,
    private val componentFactory: ComponentFactory,
) : AuthFlowComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Login,
        handleBackButton = true,
        childFactory = ::createChild,
        serializer = Config.serializer(),
    )

    override val childStack: Value<ChildStack<*, AuthFlowComponent.Child>> = stack

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): AuthFlowComponent.Child =
        when (config) {
            Config.Login -> AuthFlowComponent.Child.Login(
                componentFactory.createLoginComponent(
                    componentContext = componentContext,
                    onNavEvent = Consumer(::onLoginNavEvent),
                )
            )

            Config.Register -> AuthFlowComponent.Child.Register(
                componentFactory.createRegisterComponent(
                    componentContext = componentContext,
                    onNavEvent = Consumer(::onRegisterNavEvent),
                )
            )

            Config.ForgotPassword -> AuthFlowComponent.Child.ForgotPassword(
                componentFactory.createForgotPasswordComponent(
                    componentContext = componentContext,
                    onNavEvent = Consumer(::onForgotPasswordNavEvent),
                )
            )
        }

    private fun onLoginNavEvent(output: LoginComponent.NavEvent): Unit =
        when (output) {
            is LoginComponent.NavEvent.OpenMainFlow -> onNavEvent(AuthFlowComponent.NavEvent.OpenMainFlow)
            LoginComponent.NavEvent.OpenForgotPassword -> navigation.pushNew(Config.ForgotPassword)
            LoginComponent.NavEvent.OpenRegister -> navigation.pushNew(Config.Register)
        }

    private fun onRegisterNavEvent(output: RegisterComponent.NavEvent): Unit = when (output) {
        is RegisterComponent.NavEvent.Back -> navigation.pop()
    }

    private fun onForgotPasswordNavEvent(output: ForgotPasswordComponent.NavEvent): Unit =
        when (output) {
            is ForgotPasswordComponent.NavEvent.Back -> navigation.pop()
        }

    @Serializable
    private sealed interface Config {
        @Serializable
        object Login : Config

        @Serializable
        object Register : Config

        @Serializable
        object ForgotPassword : Config
    }
}