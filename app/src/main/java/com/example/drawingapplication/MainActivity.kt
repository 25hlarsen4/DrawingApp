package com.example.drawingapplication

import DrawCanvas
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.drawingapplication.databinding.ActivityMainActualBinding
import yuku.ambilwarna.AmbilWarnaDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import android.content.res.Configuration
import android.util.Log

// Note to self currently trying to figure how to save files to android/com.exmaple.drawingapplication.files
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels{
        DrawViewModelFactory((application as FileApplication).fileRepository, this)}

    private lateinit var drawView: DrawView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainAct", "creating")

        installSplashScreen()

        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
//                DrawViewListScreen(drawViewListViewModel = myViewModel)
               // DrawCanvas(myViewModel)
                MyApp(vm = myViewModel)
            }
        }


//        // Add Submit contact information fragment to fragment on Main Activity
//        supportFragmentManager.commit {
//            replace<DrawViewFragment>(R.id.main_screen)
//        }
//
//        // Here because fragments
//        setContentView(binding.root)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("hello", "helloooooooo")

        // Check if the orientation has changed
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("OrientationChange", "Orientation changed to Landscape")
            myViewModel.onScreenOrientationChanged(isPort = false, width = newConfig.screenWidthDp, height = newConfig.screenHeightDp)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("OrientationChange", "Orientation changed to Portrait")
            myViewModel.onScreenOrientationChanged(isPort = true, width = newConfig.screenWidthDp, height = newConfig.screenHeightDp)
        }

        // Perform any additional logic here, such as calling your ViewModel
    }

}

@Composable
fun MyApp(vm: DrawViewModel) {
    val navController = rememberNavController() // Remember the NavController

    // Define the navigation graph
    NavHost(navController = navController, startDestination = "drawingList") {
        composable("drawingList") { DrawViewListScreen(drawViewListViewModel = vm, navController = navController) }
        composable("drawingScreen") { backStackEntry ->
            DrawCanvas(vm, navController)
        }
//        composable("drawingScreen/{drawingId}") { backStackEntry ->
//            val drawingId = backStackEntry.arguments?.getString("drawingId") // Get the drawingId from arguments
//            DrawCanvas(vm, drawingId)
//        }
    }

//    // Define the navigation graph
//    NavHost(navController = navController, startDestination = "drawingScreen") {
//        composable("drawingScreen") { DrawViewScreen(vm) }
//    }

}
