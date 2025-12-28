package surface

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.w3c.dom.Text

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Preview
@Composable
fun SurfaceLearn(data: MutableList<String>) {
    val todoItems = mutableListOf<TodoItem>()
    todoItems.add(TodoItem("haha, nihao", true))
    todoItems.add(TodoItem("hahahahah, nihao", false))
    todoItems.add(TodoItem("hahafdfa, nifdfhao", true))
    todoItems.add(TodoItem("haha, nihafffdo", true))
    todoItems.add(TodoItem("haha, nihffao", false))
    todoItems.add(TodoItem("haffddha, nihao", true))
    todoItems.add(TodoItem("haha, dsadff", true))
    TodoListExample(todoItems)
}

data class TodoItem(val title: String, var completed: Boolean)

@Composable
fun TodoListExample(todoItems: List<TodoItem>) {
//    AdvancedLazyColumn()
    Timer()
}

@Composable
fun Timer() {
    var time by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        println("inline time: $time")
        while (true) {
            delay(1000)
            time ++
        }
    }

    println("outline time: $time")

    Text("Time: $time second")
}


@Composable
fun LazyColumnExample() {
    val items = listOf("Item 1", "Item 2", "Item 3", "...", "Item 1000")

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 方式1: 使用 items() 函数
        items(items) { item ->
            Text(item)
        }

        // 方式2: 使用 item() 逐个添加
        item {
            Text("Header")
        }

        items(100) { index ->
            Text("Item $index")
        }
    }
}

@Composable
fun AdvancedLazyColumn() {
    val listState = rememberLazyListState()
    val items = (1..1000).map { "Item $it" }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        reverseLayout = false, // 是否反向布局
        userScrollEnabled = true // 是否允许用户滚动
    ) {

        // 分组功能
        items.groupBy { it.first() }.forEach { (initial, groupItems) ->
            stickyHeader {
                Text(
                    "Starts with $initial",
                    modifier = Modifier.background(Color.LightGray)
                        .height(36.dp).fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
            }
            items(groupItems) { item ->
                Text(item)
            }
        }
    }

    // 监听滚动状态
    LaunchedEffect(listState.firstVisibleItemIndex) {
        println("First visible item: ${listState.firstVisibleItemIndex}")
    }
}