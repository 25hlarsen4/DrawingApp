package com.example.drawingapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.js.ExperimentalJsFileName
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

// Ayden's Repository creation for view model replace with one below if not working because of database.
// If this is active then uncomment allfiles savefiles and DrawViewModelFactory below
//class DrawViewModel(private val repository: FileRepository) : ViewModel() {
class DrawViewModel() : ViewModel() {
//    val bitmap:MutableLiveData<Bitmap> = MutableLiveData<Bitmap>(Bitmap.createBitmap(1200, 2400, Bitmap.Config.ARGB_8888))
//    private val rect: Rect by lazy {Rect(0,0, 600, 1000)}
// Bitmap is initialized with a width of 1 and height of 1 to not crash program
    var bitmap:MutableLiveData<Bitmap> = MutableLiveData<Bitmap>(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
//    val bitmapCanvas = Canvas(bitmap.value!!)

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

        var m = Matrix()
        m.postRotate(0f)

        val currentBitmap = bitmap.value!!
        var scaledBitmap =  Bitmap.createScaledBitmap(currentBitmap, screenWidth, screenHeight, false)
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

//    val allFiles: LiveData<List<FileData>> = repository.allFiles
//
//    fun addFile(fileName: String){
//        Log.e("VM", "adding file $fileName")
//        repository.addFile(fileName)
//    }

//    fun saveFile(bitmap: Bitmap, fileName: String) {
//        // Ensure external storage is available for writing
//        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//
//        // Create the file object
//        val file = File(storageDir, "$fileName.png")
//        addFile(fileName)
//        try {
//            // Open the output stream
//            val outputStream = FileOutputStream(file)
//
//            // Compress the bitmap and write it to the output stream
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//
//            // Close the output stream
//            outputStream.flush()
//            outputStream.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    fun loadFile(filename: String, context: Context): Bitmap? {
//        val file = File(context.getExternalFilesDir(null), filename)
//
//        return if (file.exists()) {
//            BitmapFactory.decodeFile(file.absolutePath)
//        } else {
//            null // Handle file not found case
//        }
//    }


}

// This factory class allows us to define custom constructors for the view model

//class DrawViewModelFactory(private val repository: FileRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DrawViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return DrawViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}