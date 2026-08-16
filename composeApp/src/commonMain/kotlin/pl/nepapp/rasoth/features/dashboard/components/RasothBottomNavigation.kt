package pl.nepapp.rasoth.features.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    BottomAppBar {
        items.forEach {
            RasothBottomAppBarItem(
                item = it
            )
        }
    }
}

@Composable
private fun RasothBottomAppBarItem(
    item: BottomNavigationItem
) {
    Column {
        IconButton(onClick = item.onClick) {

        }
        Text(
            item.text,
            style = RasothTheme.typography.regular16()
        )
    }
}