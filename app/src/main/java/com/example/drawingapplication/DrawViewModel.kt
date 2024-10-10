package com.example.drawingapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DrawViewModel : ViewModel() {
    val bitmap:MutableLiveData<Bitmap> = MutableLiveData<Bitmap>(Bitmap.createBitmap(1200, 2400, Bitmap.Config.ARGB_8888))
    private val rect: Rect by lazy {Rect(0,0, 600, 1000)}
    val bitmapCanvas = Canvas(bitmap.value!!)

    // Drawing variables
    var startX = 50f
    var startY = 50f
    var endX = 50f
    var endY  = 50f
    var paint = Paint()
    var strokeSize = 8
    var colorVal = Color.BLACK
    var shape = false

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

        paint.color = colorVal
        paint.strokeWidth = strokeSize.toFloat()

        if (shape) {
            canvas.drawCircle(startX, startY, strokeSize.toFloat(), paint)
        }else{
            canvas.drawLine(startX, startY, endX, endY, paint)
        }


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

    fun updatePenSize(newSize: Int) {
        strokeSize = newSize
    }

    fun updateColor(newColor: Int) {
        colorVal = newColor
    }

    fun getColor(): Int {
        return colorVal
    }

    fun setShape(circOrSquare: String) {
        if(circOrSquare == "circle"){
            shape = true
        }else{
            shape = false
        }
    }


}