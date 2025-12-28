package scope

import arrangement.VerticalArrangementExamples
import layout.HighPerformanceLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Composable
fun SimpleScrollableList(data: MutableList<String>) {

    // 1. 创建并记录滚动状态
    val scrollState = rememberScrollState()

    // 2. 将垂直桂东修饰符应用于 Column
    Column(
        modifier = Modifier
            .verticalScroll(scrollState) // 启动垂直滚动
            .padding(16.dp)
    ) {
        data.forEach { index ->
            Text(
                text = index,
                modifier = Modifier.padding(vertical = 4.dp),
            )
        }
    }
}

@Composable
fun HighPerformanceList(itemsList: MutableList<String>) {
    HighPerformanceLayout(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        VerticalArrangementExamples()
        val listState = rememberLazyListState()
        HorizontalDivider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "index = ${listState.firstVisibleItemIndex}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        HorizontalDivider()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            itemsIndexed(itemsList) { index, item ->
                val modifier: Modifier =
//                    if (index == 0) {
//                        Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
//                    } else {
                    Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//                    }
                Text(
                    text = "$index: $item",
                    modifier = modifier
                )
                if (index < itemsList.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun ScrollWithControl(data: MutableList<String>) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Column(Modifier.fillMaxSize()) {

        Text("当前滚动位置：${scrollState.value / 50}")

        // 控制按钮区域
        Button(
            onClick = {
                // 使用协程将滚动位置
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("滚动到顶部")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    // 滚动到指定像素位置
                    scrollState.animateScrollTo(500)
                }
            }
        ) {
            Text("滚动到500px位置")
        }

        // 可滚动内容区域
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(50.dp)
                .verticalScroll(scrollState)
        ) {
            data.forEach {
                Text(
                    it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun SimpleVerticalScope(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables: List<Measurable>, constraints: Constraints ->

        // 1. 测量: 测出所有子组件
        val placeables = measurables.map { measurable ->
            // 为每个子组件施加约束（这里允许最大宽度，高度自适应）
            measurable.measure(constraints.copy(minHeight = 0))
        }

        // 2. 计算容器总尺寸
        val totalWidth = placeables.maxOfOrNull { it.width } ?: 0
        val totalHeight = placeables.sumOf { it.height }

        // 3. 摆放：布局所有已测量的子组件
        layout(totalWidth, totalHeight) {
            var yPosition = 0
            placeables.forEach { placeable ->
                // 将每个子组件放置在垂直位置 yPosition 上
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += (placeable.height)
            }
        }
    }
}