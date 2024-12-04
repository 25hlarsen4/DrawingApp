import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import com.example.drawingapplication.DrawViewModel
import com.example.drawingapplication.PenShapeFragment
import com.example.drawingapplication.PenSizeFragment
import com.example.drawingapplication.SaveFragment
import com.example.drawingapplication.findActivity
import yuku.ambilwarna.AmbilWarnaDialog


@Composable
fun DrawCanvas(myViewModel: DrawViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Log.d("DrawCanvas", "Recomposing DrawCanvas")

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val bitmap by myViewModel.bitmap.observeAsState(initial = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888))
    val context = LocalContext.current.findActivity()
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val density = context.resources.displayMetrics.density
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        if (isLandscape) {
            println("Orientation changed: ${if (isLandscape) "Landscape" else "Portrait"}")
            myViewModel.onScreenOrientationChanged(isPort = false, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
        } else {
            println("Orientation changed: ${if (isLandscape) "Landscape" else "Portrait"}")
            myViewModel.onScreenOrientationChanged(isPort = true, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
        }
        Log.d("screenWidth",  screenWidth.toString())
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenWidth/density).dp)
                .background(Color.White)
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
            Spacer(modifier = Modifier.weight(1f))

            // Row with action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val penFragment = PenShapeFragment()
                        penFragment.show((context as FragmentActivity).supportFragmentManager, "PenShapeFragment")
                    },
                    // Make button fill available space
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Shape")
                }

                // Space between buttons
                Spacer(modifier = Modifier.width(8.dp))

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
                    // Make button fill available space
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Color")
                }

                // Space between buttons
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        val penSizeFragment = PenSizeFragment()
                        penSizeFragment.show((context as FragmentActivity).supportFragmentManager, "PenSizeFragment")
                    },
                    // Make button fill available space
                    modifier = Modifier.weight(1f)
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
                        navController.navigate("drawingList")
                        myViewModel.loadFiles(context)
                    },
                    // Make button fill available space
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Back")
                }

                // Space between buttons
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        val saveFragment = SaveFragment()
                        if (myViewModel.filename.isEmpty()) {
                            saveFragment.show((context as FragmentActivity).supportFragmentManager, "SaveFragment")
                        } else {
                            myViewModel.saveFile(bitmap, context, myViewModel.filename)
                        }
                    },
                    // Make button fill available space
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
            }
        }
    }
}


