package modifier

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Composable
fun Modifier.customModifier(
    color: Color = Color.Red,
    width: Dp = 2.dp
) = composed {
    var isPressed by remember { mutableStateOf(false) }

    Modifier
        .border(
            width = width,
            color = if (isPressed) Color.Blue else color,
            shape = RoundedCornerShape(8.dp)
        )
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                }
            )
        }
        .padding(10.dp)

    val focusRequester = FocusRequester()

    Modifier
        .focusable()            // 可获取焦点
        .focusRequester(focusRequester)  // 焦点顺序
        .onFocusChanged { focusState ->
            // 焦点变化监听
        }
        .onKeyEvent { keyEvent ->
            // 键盘事件
            keyEvent.key == Key.Enter
        }
}