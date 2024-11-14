import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import com.example.drawingapplication.DrawViewModel
import com.example.drawingapplication.PenShapeFragment
import com.example.drawingapplication.PenSizeFragment
import com.example.drawingapplication.SaveFragment
import yuku.ambilwarna.AmbilWarnaDialog


@Composable
fun DrawCanvas(myViewModel: DrawViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Log.d("DrawCanvas", "Recomposing DrawCanvas")
//    Log.d("recomp", "recomp")
//    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }
//
//    val configuration = LocalConfiguration.current
//
//// If our configuration changes then this will launch a new coroutine scope for it
//    LaunchedEffect(configuration) {
//        // Save any changes to the orientation value on the configuration object
//        snapshotFlow { configuration.orientation }
//            .collect { orientation = it }
//    }
//
//    when (orientation) {
//        Configuration.ORIENTATION_LANDSCAPE -> {
//            Log.d("config", "landscape")
////            LandscapeContent()
//            myViewModel.onScreenOrientationChanged(isPort = false, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
//        }
//
//        else -> {
//            Log.d("config", "portrait")
////            PortraitContent()
//            myViewModel.onScreenOrientationChanged(isPort = true, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
//        }
//    }

////    // LaunchedEffect will run every time the configuration changes
////    LaunchedEffect(configuration) {
////        Log.d("here", "config")
////        // Check if the screen orientation has changed (i.e., rotation)
////        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
////            // If the screen is landscape, call the necessary ViewModel method
////            myViewModel.onScreenOrientationChanged(isPort = false, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
////        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
////            // If the screen is portrait, call a different method
////            myViewModel.onScreenOrientationChanged(isPort = true, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
////        }
////    }


//    val configuration = LocalConfiguration.current
//    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
//
//    LaunchedEffect(configuration.orientation) {
//        // Log when the orientation changes (e.g., on rotation)
//        println("Orientation changed: ${if (isLandscape) "Landscape" else "Portrait"}")
//
//        if (isLandscape) {
//            // If the screen is landscape, call the necessary ViewModel method
//            myViewModel.onScreenOrientationChanged(isPort = false, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
//        } else {
//            // If the screen is portrait, call a different method
//            myViewModel.onScreenOrientationChanged(isPort = true, width = configuration.screenWidthDp, height = configuration.screenHeightDp)
//        }
//    }



    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

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
                        navController.navigate("drawingList")
                        myViewModel.loadFiles(context)
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


