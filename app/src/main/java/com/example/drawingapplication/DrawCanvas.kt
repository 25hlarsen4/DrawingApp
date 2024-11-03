import android.graphics.Bitmap
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
import yuku.ambilwarna.AmbilWarnaDialog

@Composable
fun DrawCanvas(myViewModel: DrawViewModel, modifier: Modifier = Modifier) {
    val bitmap by myViewModel.bitmap.observeAsState(initial = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888))

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray) // Set the background color to gray
    ) {
        // Drawing canvas with a white background
        Canvas(
            modifier = Modifier
                .fillMaxSize()
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
            verticalArrangement = Arrangement.SpaceBetween // Space between items
        ) {
            // Spacer to push the top buttons to the middle
            Spacer(modifier = Modifier.weight(1f)) // Pushes the next row down

            // Row with three buttons in the middle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly, // Adjust to even space
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val PenFragment = PenShapeFragment()
                        PenFragment.show((context as FragmentActivity).supportFragmentManager, "PenShapeFragment")},
                    modifier = Modifier.weight(1f) // Make buttons fill available space
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
                                    // Action when OK is pressed (color selected)
                                    myViewModel.updateColor(color)
                                }

                                override fun onCancel(dialog: AmbilWarnaDialog) {
                                    // Needs to be here, otherwise error, but functionally serves
                                    //no purpose for our app
                                }
                            })
                        // Show the color picker dialog
                        colorPicker.show()
                    },
                    modifier = Modifier.weight(1f) // Make buttons fill available space
                ) {
                    Text("Color")
                }
                Spacer(modifier = Modifier.width(8.dp)) // Space between buttons
                Button(
                    onClick = {
                        val penSizeFragment = PenSizeFragment()
                        penSizeFragment.show((context as FragmentActivity).supportFragmentManager, "PenShapeFragment")
                    },
                    modifier = Modifier.weight(1f) // Make buttons fill available space
                ) {
                    Text("Size")
                }
            }

            // Save or go back to Drawing list
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // Adjust to your layout preference
            ) {
                Button(
                    onClick = { /* Handle Button 4 click */ },
                    modifier = Modifier.weight(1f) // Make buttons fill available space
                ) {
                    Text("Back")
                }
                Spacer(modifier = Modifier.width(8.dp)) // Space between buttons
                Button(
                    onClick = { /* Handle Button 5 click */ },
                    modifier = Modifier.weight(1f) // Make buttons fill available space
                ) {
                    Text("Save")
                }
            }
        }
    }
}
