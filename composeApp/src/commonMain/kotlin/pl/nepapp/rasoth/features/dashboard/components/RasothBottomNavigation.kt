package pl.nepapp.rasoth.features.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import pl.nepapp.rasoth.core.ui.theme.RasothTheme

data class BottomNavigationItem(
    val text: String,
    val onClick: () -> Unit
)

@Composable
fun RasothBottomNavigation(
    items: ImmutableList<BottomNavigationItem>
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        items.forEach {
            RasothBottomAppBarItem(
                item = it,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun RasothBottomAppBarItem(
    item: BottomNavigationItem,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick = item.onClick, modifier = modifier) {
        Column {
            Text(
                item.text,
                style = RasothTheme.typography.regular16(),
                color = RasothTheme.colors.textColor
            )
        }
    }
}