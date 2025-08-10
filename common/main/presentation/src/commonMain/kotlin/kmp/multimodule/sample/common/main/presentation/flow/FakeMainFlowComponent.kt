package kmp.multimodule.sample.common.main.presentation.flow

import kmp.multimodule.sample.common.core.presentation.utils.createFakeChildStackValue
import kmp.multimodule.sample.common.posts.presentation.posts.FakePostsComponent

class FakeMainFlowComponent : MainFlowComponent {
    override val childStack = createFakeChildStackValue(
        MainFlowComponent.Child.Posts(FakePostsComponent())
    )

    override fun onPostsTabClicked() = Unit

    override fun onMakePostTabClicked() = Unit

    override fun onProfilesTabClicked() = Unit
}
