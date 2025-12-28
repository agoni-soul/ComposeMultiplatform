import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import surface.SurfaceLearn

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Preview
@Composable
fun TestView() {
    val data = mutableListOf<String>()
    for (i in -15..15) {
        data.add("当前列表: $i")
    }
    SurfaceLearn(data)
}
