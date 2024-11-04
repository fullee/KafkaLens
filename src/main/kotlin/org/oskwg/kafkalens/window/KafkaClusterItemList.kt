package org.oskwg.kafkalens.window

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun KafkaClusterItemList(selectedItem: String, onItemSelected: (String) -> Unit) {
    val items = listOf(
        "Item 1",
        "Item 2",
        "Item 3",
        "Item 4",
        "Item 5",
        "Item 6",
        "Item 7",
        "Item 8",
        "Item 9",
        "Item 10",
        "Item 11",
        "Item 12",
    )

    Column(modifier = Modifier.width(250.dp).border(1.dp, Color.LightGray)) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().heightIn(56.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            placeholder = { Text("Search") },
        )
        LazyColumn(
            modifier = Modifier
//                .fillMaxHeight()
                .fillMaxWidth()
                .weight(1f)
//                .height(400.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            item {
//
//            }
            items(items.size) { index ->
                val item = items[index]
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemSelected(item) }
                        .padding(16.dp),
                    color = if (item == selectedItem) Color.Blue else Color.Black
                )
            }
        }
        Row(
            modifier = Modifier.height(32.dp).background(Color.LightGray).fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,

            ) {
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = {}, modifier = Modifier.size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(0.dp)
                    .background(Color.White)
            ) {
                Icon(
                    Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = {}, modifier = Modifier.size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(0.dp)
                    .background(Color.White)
            ) {
                Icon(
                    Icons.Outlined.Delete, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color(0xFFa32929)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = {}, modifier = Modifier.size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(0.dp)
                    .background(Color.White)
            ) {
                Icon(
                    Icons.Outlined.Settings, contentDescription = null, modifier = Modifier.size(16.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewView1() {

    var selectedItem by remember { mutableStateOf("Item 1") }
    KafkaClusterItemList(selectedItem = selectedItem, onItemSelected = { selectedItem = it })
}
