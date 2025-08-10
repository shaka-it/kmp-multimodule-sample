package kmp.multimodule.sample.common.root

import kmp.multimodule.sample.common.core.presentation.utils.createFakeChildStackValue
import kmp.multimodule.sample.common.main.presentation.flow.FakeMainFlowComponent

class FakeRootComponent : RootComponent {
    override val childStack = createFakeChildStackValue(
        RootComponent.Child.MainModule(FakeMainFlowComponent())
    )
}
