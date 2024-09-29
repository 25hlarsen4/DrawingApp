
package com.example.drawingapplication

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drawingapplication.databinding.ActivityMainActualBinding


class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels()
    private lateinit var drawView: DrawView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawView = binding.drawingCanvas
        myViewModel.changeScreenDimensionsInitial(drawView.width, drawView.height)

        myViewModel.bm.observe(this) {
            drawView.invalidate()
        }

        setContentView(binding.root)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            myViewModel.changeScreenDimensions(drawView.height, drawView.width)
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            myViewModel.changeScreenDimensions(drawView.width, drawView.height)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                myViewModel.startX = event.x
                myViewModel.startY = event.y - 200
            }
            MotionEvent.ACTION_MOVE -> {
                myViewModel.endX = event.x
                myViewModel.endY = event.y - 200

                myViewModel.draw()

                myViewModel.startX = event.x
                myViewModel.startY = event.y - 200
            }
            MotionEvent.ACTION_UP -> {

                myViewModel.endX = event.x
                myViewModel.endY = event.y - 200
                myViewModel.draw()
            }
        }
        return true
    }
}
