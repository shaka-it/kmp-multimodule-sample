package kmp.multimodule.sample.common.posts.compose.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors
import kmp.multimodule.sample.common.posts.presentation.posts.FakePostsComponent
import kmp.multimodule.sample.common.posts.presentation.posts.PostsComponent
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostsScreen(
    component: PostsComponent,
    modifier: Modifier = Modifier
) {
    val state by component.viewState.subscribeAsState()
    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                isRefreshing = true
                component.onRefreshPosts()
                isRefreshing = false
            }
        }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.posts) { post ->
                PostItem(
                    title = post.title,
                    description = post.description,
                    author = post.author,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun PostItem(
    title: String,
    description: String,
    author: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = colors.primaryTextColor
        )
        Text(
            text = description,
            fontSize = 14.sp,
            color = colors.secondaryTextColor,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = author,
            fontSize = 12.sp,
            color = colors.thirdTextColor,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
private fun PostsScreenPreview() {
    AppTheme {
        PostsScreen(FakePostsComponent())
    }
}
