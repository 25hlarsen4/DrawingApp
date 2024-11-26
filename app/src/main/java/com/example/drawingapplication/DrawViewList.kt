package com.example.drawingapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DrawViewList(
    list: List<DrawViewObject>,
    onSelectedTask: (DrawViewObject) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            DrawViewItem(
                fileName = task.fileName,
                picture = task.bitmap,
                onSelected = { onSelectedTask(task) }
            )
        }
    }
}