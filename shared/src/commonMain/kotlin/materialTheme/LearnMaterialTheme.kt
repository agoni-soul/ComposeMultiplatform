package materialTheme

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composemultiplatform.shared.generated.resources.Res
import composemultiplatform.shared.generated.resources.compose_multiplatform
import getPlatformName
import org.jetbrains.compose.resources.painterResource

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Composable
fun MaterialTheme() {
    val isDarkTheme = isSystemInDarkTheme()
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val lightColorScheme = lightColorScheme(primary = Color(0xFF1EB980))

    val darkColorScheme = darkColorScheme(primary = Color(0xFF66ffc7))

    val colorScheme =
        when {
            supportsDynamicColor && isDarkTheme -> {
                dynamicDarkColorScheme(LocalContext.current)
            }

            supportsDynamicColor && !isDarkTheme -> {
                dynamicLightColorScheme(LocalContext.current)
            }

            isDarkTheme -> {
                darkColorScheme
            }

            else -> {
                lightColorScheme
            }
        }

    val typography =
        Typography(
            displaySmall = TextStyle(fontWeight = FontWeight.W100, fontSize = 96.sp),
            labelLarge = TextStyle(fontWeight = FontWeight.W600, fontSize = 14.sp),
        )

    val shapes = Shapes(extraSmall = RoundedCornerShape(3.0.dp), small = RoundedCornerShape(6.0.dp))

    MaterialTheme(colorScheme = colorScheme, typography = typography, shapes = shapes) {
        val currentTheme = if (!isSystemInDarkTheme()) "light" else "dark"
        ExtendedFloatingActionButton(
            text = { Text("FAB with text style and color from $currentTheme theme") },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Localized Description") },
            onClick = {
                println("haha, nihao")
            },
        )
    }
}

@Composable
fun MaterialTheme1() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }
        var showImage by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    greetingText = "Hello, ${getPlatformName()}"
                    showImage = !showImage
                }) {
                Text(greetingText)
            }
            AnimatedVisibility(showImage) {
                Image(
                    painterResource(Res.drawable.compose_multiplatform), null
                )
            }
        }
    }
}