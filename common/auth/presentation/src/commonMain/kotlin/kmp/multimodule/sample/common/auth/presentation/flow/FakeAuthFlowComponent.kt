package kmp.multimodule.sample.common.auth.presentation.flow

import kmp.multimodule.sample.common.auth.presentation.login.FakeLoginComponent
import kmp.multimodule.sample.common.core.presentation.utils.createFakeChildStackValue

class FakeAuthFlowComponent : AuthFlowComponent {
    override val childStack = createFakeChildStackValue(
        AuthFlowComponent.Child.Login(FakeLoginComponent())
    )
}
