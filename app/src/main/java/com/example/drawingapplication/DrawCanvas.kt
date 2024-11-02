package com.example.drawingapplication

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun DrawCanvas(myViewModel: DrawViewModel, modifier: Modifier = Modifier) {
    val bitmap by myViewModel.bitmap.observeAsState(initial = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888))
//    var bitmap by remember { mutableStateOf(Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)) }

    Canvas(modifier = modifier
        .fillMaxSize()
        .background(Color.White)
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { offset ->
                    // Reset starting positions when a new drag starts
                    myViewModel.startX = offset.x
                    myViewModel.startY = offset.y
                },
                onDrag = { change, dragAmount ->
                    change.consume()

                    // Draw on the bitmap using the view model
                    myViewModel.draw(change.position.x, change.position.y)
                }
            )
        }
    ) {
        drawImage(bitmap.asImageBitmap(), topLeft = androidx.compose.ui.geometry.Offset(0f, 0f))
    }
}