package pl.nepapp.rasoth.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.nepapp.rasoth.core.ui.theme.RasothTheme

@Composable
fun RasothScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    innerBackgroundColor: Color = RasothTheme.colors.background,
    contentWindowInsets: WindowInsets = WindowInsets.safeDrawing,
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = innerBackgroundColor,
        contentWindowInsets = contentWindowInsets,
    ) { contentPadding ->
        Box(
            modifier =
                Modifier
                    .padding(contentPadding)
                    .background(innerBackgroundColor)
                    .fillMaxSize(),
            content = content,
        )
    }
}