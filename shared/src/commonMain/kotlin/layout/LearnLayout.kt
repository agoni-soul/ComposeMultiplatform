package layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Preview
@Composable
fun HighPerformanceLayout(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier, content = content
    ) { measurables: List<Measurable>, constraints: Constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints.copy(minHeight = 0))
        }

        val totalWidth = constraints.maxWidth
        val totalHeight = placeables.sumOf { it.height }

        layout(totalWidth, totalHeight) {
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}