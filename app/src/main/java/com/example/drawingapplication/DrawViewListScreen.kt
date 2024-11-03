package com.example.drawingapplication

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun DrawViewListScreen(
    modifier: Modifier = Modifier,
    drawViewListViewModel: DrawViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) { // Ensure the column takes full height

        Button(
            onClick = {
                val displayMetrics = context.resources.displayMetrics
                val screenWidth = displayMetrics.widthPixels
                // Create a new DrawViewObject with placeholder values
                Log.d("screensize", screenWidth.toString())
                val newDrawing = DrawViewObject(999999999, "", Bitmap.createBitmap(screenWidth, screenWidth, Bitmap.Config.ARGB_8888))
                drawViewListViewModel.select(newDrawing) // Select the new drawing
                navController.navigate("drawingScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Padding for the bottom button
        ) {
            Text(text = "Create a New Drawing")
        }


        // DrawViewList below the button
        DrawViewList(
            list = drawViewListViewModel.DrawViewObjects,
            onSelectedTask = { drawViewObject ->
                drawViewListViewModel.select(drawViewObject)
                navController.navigate("drawingScreen")
            },
            modifier = Modifier.weight(1f) // Take the remaining space
        )
    }
}
