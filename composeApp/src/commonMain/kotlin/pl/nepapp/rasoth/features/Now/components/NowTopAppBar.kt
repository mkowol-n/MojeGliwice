package pl.nepapp.rasoth.features.Now.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import org.jetbrains.compose.resources.painterResource
import rasoth.composeapp.generated.resources.Res
import rasoth.composeapp.generated.resources.test_image

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowTopAppBar(
    progress: Float
) {
    val density = LocalDensity.current
    val p = progress.coerceIn(0f, 1f)

    var mojeWidth by remember { mutableIntStateOf(0) }
    var boxHeight by remember { mutableIntStateOf(0) }

    Row {
        Box(
            modifier = Modifier.onGloballyPositioned {
                boxHeight = it.size.height
            }
        ) {
            Text(
                text = "Moje",
                fontSize = 28.sp,
                modifier = Modifier.onGloballyPositioned {
                    mojeWidth = it.size.width
                }
            )

            Text(
                text = "Gliwice",
                fontSize = 28.sp,
                modifier = Modifier.padding(
                    start = with(density) {
                        (mojeWidth * p).toDp()
                    },
                    top = with(density) {
                        (28.sp.toPx() * (1f - p)).toDp()
                    }
                )
            )
        }

        if (boxHeight > 0) {
            Image(
                painter = painterResource(Res.drawable.test_image),
                contentDescription = null,
                modifier = Modifier
                    .size(with(density) { boxHeight.toDp() })
                    .alpha(1f - p)
            )
        }
    }
}