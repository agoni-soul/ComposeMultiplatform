import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * @auther: haha
 * @Date:   2025/12/12
 * @Detail:
 */
@Preview
@OptIn(ExperimentalResourceApi::class, InternalResourceApi::class)
@Composable
fun App() {
    TestView()
}

expect fun getPlatformName(): String
