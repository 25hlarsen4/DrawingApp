package com.example.drawingapplication

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity

@Composable
fun DrawViewListScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val navController = (context as MainActivity).navController

    val drawViewListViewModel: DrawViewModel = (context as MainActivity).myViewModel

    Column(modifier = modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("sharing")
                },
                // Make button fill available space
                modifier = Modifier.weight(1f)
            ) {
                Text("Import")
            }

            // Space between buttons
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    navController.navigate("sharing")
                },
                // Make button fill available space
                modifier = Modifier.weight(1f)
            ) {
                Text("Export")
            }
        }
        Button(
            onClick = {
                val displayMetrics = context.resources.displayMetrics
                val screenWidth = displayMetrics.widthPixels
                // Create a new DrawViewObject with placeholder values
                Log.d("screensize", screenWidth.toString())
                val newDrawing = DrawViewObject(999999999, "", Bitmap.createBitmap(screenWidth, screenWidth, Bitmap.Config.ARGB_8888))
                // Select the new drawing
                drawViewListViewModel.select(newDrawing)
                navController.navigate("drawingScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
            modifier = Modifier.weight(1f)
        )
    }
}
