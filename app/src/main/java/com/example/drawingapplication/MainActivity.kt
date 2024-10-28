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


class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels()
    private lateinit var drawView: DrawView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // Add Submit contact information fragment to fragment on Main Activity
        supportFragmentManager.commit {
            replace<DrawViewFragment>(R.id.main_screen)
        }


        setContentView(binding.root)
    }

}
