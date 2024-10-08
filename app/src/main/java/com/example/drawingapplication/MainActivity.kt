
package com.example.drawingapplication

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drawingapplication.databinding.ActivityMainActualBinding
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {
    val binding: ActivityMainActualBinding by lazy {ActivityMainActualBinding.inflate(layoutInflater)}
    val myViewModel: DrawViewModel by viewModels()
    private lateinit var drawView: DrawView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawView = binding.drawingCanvas

        myViewModel.bm.observe(this) {
            drawView.invalidate()
        }

        binding.penButton.setOnClickListener {
            val penSizeFragment = PenSizeFragment()
            penSizeFragment.show(supportFragmentManager, "pen_size_fragment")
        }

        binding.colorButton.setOnClickListener {
            val colorPicker = AmbilWarnaDialog(
                this,
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
        }

        setContentView(binding.root)
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
                myViewModel.draw()

                myViewModel.endX = event.x
                myViewModel.endY = event.y - 200
            }
        }
        return true
    }
}
