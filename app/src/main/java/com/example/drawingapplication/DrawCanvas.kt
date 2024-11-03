import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.drawingapplication.DrawViewModel
import com.example.drawingapplication.PenShapeFragment
import com.example.drawingapplication.PenSizeFragment
import com.example.drawingapplication.SaveFragment
import yuku.ambilwarna.AmbilWarnaDialog


@Composable
fun DrawCanvas(myViewModel: DrawViewModel, modifier: Modifier = Modifier) {
    val bitmap by myViewModel.bitmap.observeAsState(initial = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888))
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val density = context.resources.displayMetrics.density
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray) // Background color
    ) {
        Log.d("screenWidth",  screenWidth.toString())
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenWidth/density).dp)
                .background(Color.White) // Canvas background
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            myViewModel.startX = offset.x
                            myViewModel.startY = offset.y
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            myViewModel.draw(change.position.x, change.position.y)
                        }
                    )
                }
        ) {
            drawImage(bitmap.asImageBitmap(), topLeft = androidx.compose.ui.geometry.Offset(0f, 0f))
        }

        val context = LocalContext.current

        // Main Column for Buttons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f)) // Pushes buttons down

            // Row with action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween, // Even spacing
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val penFragment = PenShapeFragment()
                        penFragment.show((context as FragmentActivity).supportFragmentManager, "PenShapeFragment")
                    },
                    modifier = Modifier.weight(1f) // Make button fill available space
                ) {
                    Text("Shape")
                }

                Spacer(modifier = Modifier.width(8.dp)) // Space between buttons

                Button(
                    onClick = {
                        val colorPicker = AmbilWarnaDialog(
                            context,
                            myViewModel.getColor(),
                            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                                override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                                    myViewModel.updateColor(color)
                                }

                                override fun onCancel(dialog: AmbilWarnaDialog) {}
                            })
                        colorPicker.show()
                    },
                    modifier = Modifier.weight(1f) // Make button fill available space
                ) {
                    Text("Color")
                }

                Spacer(modifier = Modifier.width(8.dp)) // Space between buttons

                Button(
                    onClick = {
                        val penSizeFragment = PenSizeFragment()
                        penSizeFragment.show((context as FragmentActivity).supportFragmentManager, "PenSizeFragment")
                    },
                    modifier = Modifier.weight(1f) // Make button fill available space
                ) {
                    Text("Size")
                }
            }

            // Save or go back
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // This needs to go to homescreen
                    },
                    modifier = Modifier.weight(1f) // Make button fill available space
                ) {
                    Text("Back")
                }

                Spacer(modifier = Modifier.width(8.dp)) // Space between buttons

                Button(
                    onClick = {
                        val saveFragment = SaveFragment()
                        if (myViewModel.filename.isEmpty()) {
                            saveFragment.show((context as FragmentActivity).supportFragmentManager, "SaveFragment")
                        } else {
                            myViewModel.saveFile(bitmap, context, myViewModel.filename)
                        }
                    },
                    modifier = Modifier.weight(1f) // Make button fill available space
                ) {
                    Text("Save")
                }
            }
        }
    }
}


