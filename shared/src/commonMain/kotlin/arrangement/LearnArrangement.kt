package arrangement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * @auther: haha
 * @Date:   2025/12/19
 * @Detail:
 */
@Preview
@Composable
fun VerticalArrangementExamples() {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = listState,
    ) {
        // 1. Top - 顶部对齐（默认）
        item {
            ExampleItem("1. Top (默认)") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray).height(200.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    ItemBox("A")
                    ItemBox("B")
                    ItemBox("C")
                }
            }
        }
        // 2. Center - 垂直居中
        item {
            ExampleItem("2. Center") {
                Column(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    ItemBox("A")
                    ItemBox("B")
                    ItemBox("C")
                }
            }
        }

        // 3. Bottom - 底部对齐
        item {
            ExampleItem("3. Bottom") {
                Column(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ItemBox("A")
                    ItemBox("B")
                    ItemBox("C")
                }
            }
        }

        // 4. SpaceBetween - 两端分布
        item {
            ExampleItem("4. SpaceBetween") {
                Column(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemBox("A")
                    ItemBox("B")
                    ItemBox("C")
                }
            }
        }

        // 5. SpaceAround - 周围分布
        item {
            ExampleItem("5. SpaceAround") {
                Column(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    ItemBox("A")
                    ItemBox("B")
                    ItemBox("C")
                }
            }
        }

        // 6. SpaceEvenly - 均匀分布
        item {
            ExampleItem("6. SpaceEvenly") {
                Column(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ItemBox("A")
                    ItemBox("B")
                    ItemBox("C")
                }
            }
        }
    }
}

@Composable
fun ExampleItem(title: String, content: @Composable () -> Unit) {
    Text(title)
    content()
}

@Composable
fun CustomVerticalArrangement() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp) // 固定间距 20dp
    ) {
        ItemBox("A")
        ItemBox("B")
        ItemBox("C")
    }
}

// 更复杂的自定义排列
@Composable
fun ComplexVerticalArrangement() {
    Column(
        modifier = Modifier.height(200.dp),
        verticalArrangement = Arrangement.aligned(Alignment.Top) // 顶部对齐
    ) {
        ItemBox("A")
        Spacer(modifier = Modifier.weight(1f)) // 弹性空间
        ItemBox("B")
    }
}

@Composable
fun ItemBox(text: String) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.Black)
    }
}