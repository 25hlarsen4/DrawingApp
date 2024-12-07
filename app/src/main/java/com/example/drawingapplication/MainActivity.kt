package com.example.drawingapplication

import DrawCanvas
import LoginScreen
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.drawingapplication.databinding.ActivityMainActualBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.res.Configuration
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController

internal fun Context.findActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}


// Note to self currently trying to figure how to save files to android/com.exmaple.drawingapplication.files
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels{
        DrawViewModelFactory((application as FileApplication).fileRepository, this)}
    lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainAct", "creating")

        installSplashScreen()

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }

        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                navController = rememberNavController()

                // Define the navigation graph
                NavHost(navController = navController as NavHostController, startDestination = "login") {
                    composable("login") { LoginScreen(navController = navController as NavHostController) }
                    composable("drawingList") { DrawViewListScreen(drawViewListViewModel = myViewModel, navController = navController as NavHostController) }
                    composable("sharing") { SharingScreen(drawViewListViewModel = myViewModel, navController = navController as NavHostController) }
                    composable("drawingScreen") { DrawCanvas(myViewModel,
                        navController as NavHostController
                    ) }
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Check if the orientation has changed
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("OrientationChange", "Orientation changed to Landscape")
            myViewModel.onScreenOrientationChanged(isPort = false, width = newConfig.screenWidthDp, height = newConfig.screenHeightDp)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("OrientationChange", "Orientation changed to Portrait")
            myViewModel.onScreenOrientationChanged(isPort = true, width = newConfig.screenWidthDp, height = newConfig.screenHeightDp)
        }
    }
}
