package com.example.drawingapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.graphics.createBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DrawViewModel : ViewModel() {
    // Bitmap is initialized with a width of 1 and height of 1 to not crash program
    var bitmap:MutableLiveData<Bitmap> = MutableLiveData<Bitmap>(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
    var bitmapCanvas = Canvas(bitmap.value!!)

    // Drawing variables
    var startX = 50f
    var startY = 50f
    var endX = 50f
    var endY  = 50f
    var paint = Paint()

    // Screen Dimensions
    var screenWidth = 1200
    var screenHeight = 2400

    // First run
    var first = true
    var isPortrait = false
    var change = false

    // LiveData
    var bm = bitmap as LiveData<Bitmap>

    @RequiresApi(Build.VERSION_CODES.O)
    fun draw() {
        // Get bitmap
        val currentBitmap = bitmap.value!!
        val canvas = Canvas(currentBitmap)
        
        paint.color = Color.BLACK
        paint.strokeWidth = 8f

        canvas.drawLine(startX, startY, endX, endY, paint)

        // Notify observers about the updated bitmap
        bitmap.value = currentBitmap
    }

    fun changeScreenDimensions(width: Int, height: Int){
        if (width <= 0 || height <= 0)
            return
        screenWidth = width
        screenHeight = height

        if (first) {
            if (width < height)
            {
                isPortrait = true
            }
            first = false
        }

        var m = Matrix()
        m.postRotate(0f)
        if (width > height && isPortrait == true && !first)
        {
            m.postRotate(-90f)
            change = true
            isPortrait = false
        }
        else if (height > width && isPortrait == false && !first)
        {
            m.postRotate(90f)
            change = true
            isPortrait = true
        }
        val currentBitmap = bitmap.value!!
        var scaledBitmap =  Bitmap.createScaledBitmap(currentBitmap, screenWidth, screenHeight, false)
        if (change) {
            scaledBitmap = Bitmap.createScaledBitmap(currentBitmap, screenHeight, screenWidth, false)
        }

        change = false
        bitmap.value  = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), m, true)
    }
}