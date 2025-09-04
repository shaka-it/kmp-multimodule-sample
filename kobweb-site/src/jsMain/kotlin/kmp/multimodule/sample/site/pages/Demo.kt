package kmp.multimodule.sample.site.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.text.SpanText
import kmp.multimodule.sample.site.components.layouts.PageLayoutData
import kmp.multimodule.sample.site.data.Post
import kmp.multimodule.sample.site.data.PostsRepository
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba

@InitRoute
fun initDemoPage(ctx: InitRouteContext) { ctx.data.add(PageLayoutData("Demo")) }

private sealed interface UiState<out T> {
    object Idle : UiState<Nothing>
    object Loading : UiState<Nothing>
    data class Data<T>(val value: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun DemoPage() {
    var state by remember { mutableStateOf<UiState<List<Post>>>(UiState.Loading) }
    val scope = rememberCoroutineScope()

    fun refresh() = scope.launch {
        state = UiState.Loading
        state = runCatching { PostsRepository.load() }
            .fold(onSuccess = { UiState.Data(it) },
                onFailure = { UiState.Error(it.message ?: "Unknown error") })
    }

    LaunchedEffect(Unit) { refresh() }

    Column(
        modifier = Modifier
            .maxWidth(48.cssRem)
            .margin(top = 1.cssRem)
            .gap(1.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.gap(0.5.cssRem)) {
            Button(onClick = { refresh() }) { SpanText("Refresh") }
        }

        Box(Modifier.height(1.cssRem))

        when (val s = state) {
            UiState.Idle, UiState.Loading -> LoadingCard()
            is UiState.Error -> ErrorCard(s.message)
            is UiState.Data -> PostsList(s.value)
        }
    }
}

@Composable
private fun LoadingCard() {
    Column(
        modifier = Modifier
            .borderRadius(0.5.cssRem)
            .border(width = 1.px, color = rgba(0,0,0,0.1))
            .padding(1.cssRem)
    ) { SpanText("Loading…") }
}

@Composable
private fun ErrorCard(msg: String) {
    Column(
        modifier = Modifier
            .borderRadius(0.5.cssRem)
            .border(width = 1.px, color = rgba(255,0,0,0.25))
            .padding(1.cssRem)
    ) { SpanText("Error: $msg") }
}

@Composable
private fun PostsList(items: List<Post>) {
    Column(modifier = Modifier.gap(0.75.cssRem)) {
        items.forEach { PostCard(it) }
    }
}

@Composable
private fun PostCard(post: Post) {
    Column(
        modifier = Modifier
            .borderRadius(0.75.cssRem)
            .border(width = 1.px, color = rgba(0,0,0,0.1))
            .padding(1.cssRem)
            .gap(0.25.cssRem)
    ) {
        SpanText("#${post.id}: ${post.title}")
        SpanText(post.body)
    }
}