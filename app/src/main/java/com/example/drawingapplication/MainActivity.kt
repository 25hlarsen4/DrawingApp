package com.example.drawingapplication

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
import androidx.compose.ui.Modifier

// Note to self currently trying to figure how to save files to android/com.exmaple.drawingapplication.files
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels{
        DrawViewModelFactory((application as FileApplication).fileRepository, this)}

    private lateinit var drawView: DrawView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                DrawViewListScreen(drawViewListViewModel = myViewModel)
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

}
