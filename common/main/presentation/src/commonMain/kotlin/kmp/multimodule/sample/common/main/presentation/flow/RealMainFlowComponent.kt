package kmp.multimodule.sample.common.main.presentation.flow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kmp.multimodule.sample.common.core.presentation.component.ComponentFactory
import kmp.multimodule.sample.common.core.presentation.utils.Consumer
import kmp.multimodule.sample.common.core.presentation.utils.invoke
import kmp.multimodule.sample.common.main.presentation.flow.MainFlowComponent.NavEvent
import kmp.multimodule.sample.common.posts.presentation.createMakePostComponent
import kmp.multimodule.sample.common.posts.presentation.createPostsComponent
import kmp.multimodule.sample.common.profile.presentation.ProfileComponent
import kmp.multimodule.sample.common.profile.presentation.createProfileComponent
import kotlinx.serialization.Serializable

class RealMainFlowComponent(
    componentContext: ComponentContext,
    private val onNavEvent: Consumer<NavEvent>,
    private val componentFactory: ComponentFactory,
) : MainFlowComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Posts,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override val childStack: Value<ChildStack<*, MainFlowComponent.Child>> = stack

    override fun onPostsTabClicked() {
        navigation.bringToFront(Config.Posts)
    }

    override fun onMakePostTabClicked() {
        navigation.bringToFront(Config.MakePost)
    }

    override fun onProfilesTabClicked() {
        navigation.bringToFront(Config.Profile)
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): MainFlowComponent.Child =
        when (config) {
            Config.Posts -> MainFlowComponent.Child.Posts(
                componentFactory.createPostsComponent(componentContext = componentContext)
            )

            Config.MakePost -> MainFlowComponent.Child.MakePost(
                componentFactory.createMakePostComponent(componentContext = componentContext)
            )

            Config.Profile -> MainFlowComponent.Child.Profile(
                componentFactory.createProfileComponent(
                    componentContext = componentContext,
                    onNavEvent = Consumer(::onProfileNavEvent),
                )
            )
        }

    private fun onProfileNavEvent(output: ProfileComponent.NavEvent): Unit =
        when (output) {
            is ProfileComponent.NavEvent.OpenAuthFlow -> onNavEvent(NavEvent.OpenAuthFlow)
        }

    @Serializable
    private sealed interface Config {
        @Serializable
        object Posts : Config

        @Serializable
        object MakePost : Config

        @Serializable
        object Profile : Config
    }
}