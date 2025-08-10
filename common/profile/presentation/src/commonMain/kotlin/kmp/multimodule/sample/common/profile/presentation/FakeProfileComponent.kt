package kmp.multimodule.sample.common.profile.presentation

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class FakeProfileComponent : ProfileComponent {
    override val viewState: Value<ProfileComponent.ViewState>
        get() = MutableValue(ProfileComponent.ViewState())

    override fun onLogoutClicked() = Unit
}