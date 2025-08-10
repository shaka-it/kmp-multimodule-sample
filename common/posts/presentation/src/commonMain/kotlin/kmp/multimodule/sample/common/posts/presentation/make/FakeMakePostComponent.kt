package kmp.multimodule.sample.common.posts.presentation.make

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class FakeMakePostComponent : MakePostComponent {
    override val viewState: Value<MakePostComponent.ViewState>
        get() = MutableValue(MakePostComponent.ViewState())

    override fun onTitleChanged(value: String) = Unit

    override fun onDescriptionChanged(value: String) = Unit

    override fun onMakePostClick() = Unit
}