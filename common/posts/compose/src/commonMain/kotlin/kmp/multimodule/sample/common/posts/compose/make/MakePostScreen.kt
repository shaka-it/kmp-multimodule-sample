package kmp.multimodule.sample.common.posts.compose.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kmp.multimodule.sample.common.core.compose.components.ThemedButton
import kmp.multimodule.sample.common.core.compose.components.ThemedTextField
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors
import kmp.multimodule.sample.common.posts.presentation.make.FakeMakePostComponent
import kmp.multimodule.sample.common.posts.presentation.make.MakePostComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MakePostScreen(
    component: MakePostComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.viewState.subscribeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.primaryBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        ThemedTextField(
            value = state.title,
            onValueChange = component::onTitleChanged,
            label = "Title",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ThemedTextField(
            value = state.description,
            onValueChange = component::onDescriptionChanged,
            label = "Description",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            singleLine = false,
            imeAction = ImeAction.Default
        )

        Spacer(modifier = Modifier.height(24.dp))

        ThemedButton(
            text = "Post",
            onClick = component::onMakePostClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}

@Preview
@Composable
private fun MakePostScreenPreview() {
    AppTheme {
        MakePostScreen(FakeMakePostComponent())
    }
}
