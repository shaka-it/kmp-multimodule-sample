package kmp.multimodule.sample.common.auth.presentation

import com.arkivanov.decompose.ComponentContext
import kmp.multimodule.sample.common.auth.presentation.forgot.ForgotPasswordComponent
import kmp.multimodule.sample.common.auth.presentation.forgot.RealForgotPasswordComponent
import kmp.multimodule.sample.common.auth.presentation.login.LoginComponent
import kmp.multimodule.sample.common.auth.presentation.login.RealLoginComponent
import kmp.multimodule.sample.common.auth.presentation.register.RealRegisterComponent
import kmp.multimodule.sample.common.auth.presentation.register.RegisterComponent
import kmp.multimodule.sample.common.auth.presentation.flow.AuthFlowComponent
import kmp.multimodule.sample.common.auth.presentation.flow.RealAuthFlowComponent
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.get

fun ComponentFactory.createAuthModuleComponent(
    componentContext: ComponentContext,
    onNavEvent: Consumer<AuthFlowComponent.NavEvent>,
): AuthFlowComponent {
    return RealAuthFlowComponent(
        componentContext = componentContext,
        onNavEvent = onNavEvent,
        componentFactory = get(),
    )
}

fun ComponentFactory.createLoginComponent(
    componentContext: ComponentContext,
    onNavEvent: Consumer<LoginComponent.NavEvent>,
): LoginComponent {
    return RealLoginComponent(
        componentContext = componentContext,
        onNavEvent = onNavEvent,
        mainContext = Dispatchers.Main.immediate,
        authRepository = get(),
    )
}

fun ComponentFactory.createRegisterComponent(
    componentContext: ComponentContext,
    onNavEvent: Consumer<RegisterComponent.NavEvent>,
): RegisterComponent {
    return RealRegisterComponent(
        componentContext = componentContext,
        onNavEvent = onNavEvent,
    )
}

fun ComponentFactory.createForgotPasswordComponent(
    componentContext: ComponentContext,
    onNavEvent: Consumer<ForgotPasswordComponent.NavEvent>,
): ForgotPasswordComponent {
    return RealForgotPasswordComponent(
        componentContext = componentContext,
        onNavEvent = onNavEvent,
    )
}