package com.example.drawingapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter


@Composable
fun DrawViewItem(
    fileName: String,
    onSelected: () -> Unit,
    picture: Bitmap,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = fileName
        )
        Image(
            bitmap = picture.asImageBitmap(),
            contentDescription = "Current picture of Drawing",
            modifier = Modifier.size(128.dp)
        )
        Button(
            modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp),
            onClick = onSelected)
        { Text(text = "Select")}
    }
}