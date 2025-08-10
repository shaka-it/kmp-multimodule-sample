package kmp.multimodule.sample.common.profile.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.seiko.imageloader.rememberImagePainter
import kmp.multimodule.sample.common.core.compose.components.ThemedButton
import kmp.multimodule.sample.common.core.compose.theme.AppTheme
import kmp.multimodule.sample.common.core.compose.theme.Theme.colors
import kmp.multimodule.sample.common.profile.presentation.FakeProfileComponent
import kmp.multimodule.sample.common.profile.presentation.ProfileComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    component: ProfileComponent,
    modifier: Modifier = Modifier
) {
    val state by component.viewState.subscribeAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.primaryBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(colors.secondaryTextColor),
            painter = rememberImagePainter(state.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.login,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colors.primaryTextColor
        )

        Spacer(modifier = Modifier.weight(1f))

        ThemedButton(
            text = "Logout",
            onClick = component::onLogoutClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(component = FakeProfileComponent())
    }
}