package pl.nepapp.rasoth.features.Now

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import pl.nepapp.rasoth.core.navigation.BaseScreen
import pl.nepapp.rasoth.core.ui.RasothScaffold
import pl.nepapp.rasoth.features.Now.components.NowTopAppBar

@Serializable
object NowScreen: BaseScreen {
    @Composable
    override fun Content() {
        NowContent()
    }
}

@Composable
private fun NowContent() {
    val listState = rememberLazyListState()
    val scrollOffset by remember {
        derivedStateOf {
            val firstItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()

            if (firstItem == null) {
                0
            } else {
                firstItem.index * firstItem.size - firstItem.offset
            }
        }
    }

    val progress = (scrollOffset / 200f).coerceIn(0f, 1f)
    RasothScaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            NowTopAppBar(progress = progress)
        }
    ) {
        LazyColumn(
            state = listState
        ) {
            items(100) {
                Text("Halo")
            }
        }
    }
}