package com.example.drawingapplication

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawViewListScreen(
    modifier: Modifier = Modifier,
    drawViewListViewModel: DrawViewModel,
    navController: NavHostController
) {
    Column(modifier = modifier) {
        DrawViewList(
            list = drawViewListViewModel.DrawViewObjects,
            onSelectedTask = { drawViewObject ->
                drawViewListViewModel.select(drawViewObject)
                navController.navigate("drawingScreen")
            }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Optional spacer for some space

        Button(
            onClick = {
                // Create a new DrawViewObject with placeholder values
                val newDrawing = DrawViewObject(999999999, "", Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
                drawViewListViewModel.select(newDrawing) // Select the new drawing
                navController.navigate("drawingScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Padding for the bottom button
        ) {
            Text(text = "Create a New Drawing")
        }
    }
}