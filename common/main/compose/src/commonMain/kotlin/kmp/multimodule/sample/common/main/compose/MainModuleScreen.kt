package kmp.multimodule.sample.common.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors
import kmp.multimodule.sample.common.main.presentation.flow.FakeMainFlowComponent
import kmp.multimodule.sample.common.main.presentation.flow.MainFlowComponent
import kmp.multimodule.sample.common.posts.compose.make.MakePostScreen
import kmp.multimodule.sample.common.posts.compose.posts.PostsScreen
import kmp.multimodule.sample.common.profile.compose.ProfileScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainModuleScreen(
    component: MainFlowComponent,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.background(color = colors.primaryBackground)) {
        Children(
            component = component,
            modifier = Modifier
                .weight(1F)
                .consumeWindowInsets(WindowInsets.navigationBars),
        )
        BottomTabBar(
            component = component,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun Children(component: MainFlowComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is MainFlowComponent.Child.Posts -> PostsScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize(),
            )

            is MainFlowComponent.Child.MakePost -> MakePostScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )

            is MainFlowComponent.Child.Profile -> ProfileScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BottomTabBar(
    component: MainFlowComponent,
    modifier: Modifier = Modifier,
) {
    val stack by component.childStack.subscribeAsState()
    val activeComponent = stack.active.instance

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TabComponent(
                modifier = Modifier
                    .clickable(onClick = component::onPostsTabClicked),
                title = "Posts",
                image = Icons.Default.Home,
                isSelected = activeComponent is MainFlowComponent.Child.Posts,
            )
            TabComponent(
                modifier = Modifier
                    .clickable(onClick = component::onMakePostTabClicked),
                title = "Make Post",
                image = Icons.Default.AddBox,
                isSelected = activeComponent is MainFlowComponent.Child.MakePost,
            )
            TabComponent(
                modifier = Modifier
                    .clickable(onClick = component::onProfilesTabClicked),
                title = "Profile",
                image = Icons.Default.Person,
                isSelected = activeComponent is MainFlowComponent.Child.Profile,
            )
        }
    }
}

@Composable
private fun TabComponent(
    modifier: Modifier = Modifier,
    title: String,
    image: ImageVector,
    isSelected: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            tint = if (isSelected) colors.primaryAction else colors.hintTextColor,
            contentDescription = null,
            imageVector = image,
        )
        Spacer(Modifier.height(4.dp))
        Text(title, color = if (isSelected) colors.primaryAction else colors.hintTextColor)
    }
}

@Preview
@Composable
private fun MainModuleScreenPreview() {
    AppTheme {
        MainModuleScreen(FakeMainFlowComponent())
    }
}
