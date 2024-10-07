
package com.example.drawingapplication

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.drawingapplication.databinding.ActivityMainActualBinding


class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels()
    private lateinit var drawView: DrawView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Getting rid of title and action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        supportActionBar!!.hide()

        // Getting the draw view
        drawView = binding.drawingCanvas

        // Observing drawView for changes
        myViewModel.bm.observe(this) {
            drawView.invalidate()
        }

        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // If event is null return instantly
        event ?: return false

        // If event isn't null then see what action the user did
        when (event.action) {
            // If the user presses click then record initial position
            MotionEvent.ACTION_DOWN -> {
                myViewModel.startX = event.x
                myViewModel.startY = event.y - 60
            }
            // If the user moves the mouse while clicked then record it as the end position then draw.
            // After drawing record position in case user moves cursor again.
            MotionEvent.ACTION_MOVE -> {
                myViewModel.endX = event.x
                myViewModel.endY = event.y - 60

                myViewModel.draw()

                myViewModel.startX = event.x
                myViewModel.startY = event.y - 60
            }
            // If the user lets go of click then record position then draw.
            MotionEvent.ACTION_UP -> {

                myViewModel.endX = event.x
                myViewModel.endY = event.y - 60
                myViewModel.draw()
            }
        }
        return true
    }
}
