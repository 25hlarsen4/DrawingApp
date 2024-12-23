package com.example.drawingapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.io.FileOutputStream
import androidx.compose.runtime.toMutableStateList
import com.google.firebase.storage.StorageReference


private fun getDrawViewObjects() = List(0) {i -> DrawViewObject(i, "", Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) )}

class DrawViewModel(private val repository: FileRepository, context: Context) : ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> get() = _bitmap

    // Compose variables
    private val _DrawViewObjects = getDrawViewObjects().toMutableStateList()
    val DrawViewObjects: List<DrawViewObject>
        get() = _DrawViewObjects

    // Drawing variables
    var startX = 50f
    var startY = 50f
    var endX = 50f
    var endY  = 50f
    var paint = Paint()
    var strokeSize = 8
    var colorVal = Color.BLACK
    var shape = false
    var filename = ""

    var screenWidth = 1200

    val allFiles: LiveData<List<FileData>> = repository.allFiles

    var export = false

    init {
        _bitmap.value = Bitmap.createBitmap(screenWidth, screenWidth, Bitmap.Config.ARGB_8888)

        allFiles.observeForever { files ->
            files?.let {
                val drawViewObjectList = mutableListOf<DrawViewObject>()
                for (i in it.indices) {
                    val file = it[i]
                    drawViewObjectList.add(DrawViewObject(i, file.filename, loadFile(file.filename, context)))
                }
                _DrawViewObjects.addAll(drawViewObjectList)
            }
        }
    }


    fun select(item: DrawViewObject) {
        Log.d("Selected", item.fileName.toString())
        filename = item.fileName.toString()
        Log.d("Filename", filename)
        _bitmap.value = item.bitmap
    }

    fun draw(currentX: Float, currentY: Float) {
        val currentBitmap = bitmap.value!!.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = android.graphics.Canvas(currentBitmap)

        paint.color = colorVal
        paint.strokeWidth = strokeSize.toFloat()

        // Draw line from the last position to the current position
        if (shape) {
            canvas.drawCircle(currentX, currentY, strokeSize.toFloat(), paint)
        }
        else {
            canvas.drawLine(startX, startY, currentX, currentY, paint)
        }

        // Update start position for the next draw
        startX = currentX
        startY = currentY

        _bitmap.value = currentBitmap
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

    fun addFile(fileName: String){
        Log.e("VM", "adding file $fileName")
        repository.addFile(fileName)
    }

    fun loadFiles(context: Context) {
        Log.d("Files", _DrawViewObjects.toString())
        _DrawViewObjects.clear()

        Log.d("Files", _DrawViewObjects.toString())
        allFiles.value?.let { files ->
            val drawViewObjectList = files.mapIndexed { i, file ->
                DrawViewObject(i, file.filename, loadFile(file.filename, context))
            }
            _DrawViewObjects.addAll(drawViewObjectList)
        }
        Log.d("Files", _DrawViewObjects.toString())
    }

    fun saveFile(bitmap: Bitmap, context: Context, fileName: String) {
        // Ensure external storage is available for writing
        var fileName = fileName
        if (fileName.endsWith(".png"))
        {
            fileName = fileName.replace(".png", "")
        }
        val file = File(context.getExternalFilesDir(null), "$fileName.png")

        var fileExists = false
        for (fileData in allFiles.value!!)
        {
            Log.d("SaveFileCheck", fileData.filename)
            if (fileData.filename == this.filename)
            {
                Log.d("SaveFileCheckFileExists", fileData.filename)
                fileExists = true
                break
            }
        }
        if (!fileExists)
        {
            Log.d("Adding to Database", fileName)
            addFile("$fileName.png")
        }
        Log.d("Saving File", fileName)
        try {
            // Open the output stream
            val outputStream = FileOutputStream(file)

            // Compress the bitmap and write it to the output stream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

            // Close the output stream
            outputStream.flush()
            outputStream.close()

            // to get updates to immediately show in lazy column
            loadFiles(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadFile(filename: String, context: Context): Bitmap {
        val file = File(context.getExternalFilesDir(null), filename)

        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath) ?: run {
                Log.d("LoadFile", "Failed to decode bitmap from: ${file.absolutePath}")
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Placeholder
            }
        } else {
            Log.d("LoadFile", "File not found: ${file.absolutePath}")
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Placeholder
        }
    }

    //returns whether the upload was successful
    fun uploadData(ref: StorageReference, path: String, data: ByteArray): Boolean {
        val fileRef = ref.child(path)

            val uploadTask = fileRef.putBytes(data)
            uploadTask
                .addOnFailureListener { e ->
                    Log.e("PICUPLOAD", "Failed !$e")

                }
                .addOnSuccessListener {
                    Log.d("PICUPLOAD", "success")

                }
        return true
    }

    fun downloadImage(ref: StorageReference, path: String, callback: (Bitmap?) -> Unit) {
        val fileRef = ref.child(path)

        // Perform the download operation
        fileRef.getBytes(10 * 1024 * 1024)  // Max 10 MB
            .addOnSuccessListener { bytes ->
                // Decode the byte array into a Bitmap
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                callback(bitmap)  // Pass the bitmap to the callback
            }
            .addOnFailureListener { exception ->
                Log.e("DOWNLOAD_IMAGE", "Failed to get image: $exception")
                callback(null)  // Call callback with null if failed
            }
    }

fun downloadAllImages(ref: StorageReference, directoryPath: String, callback: (MutableList<DrawViewObject>) -> Unit) {
    val fileList: MutableList<DrawViewObject> = mutableListOf()

    // List all the items in the directory
    ref.listAll()
        .addOnSuccessListener { result ->
            var count = 0
            val totalItems = result.items.size
            var downloadedCount = 0

            // Loop through each item and download the image
            for (item in result.items) {
                downloadImage(ref, item.path) { bitmap ->
                    if (bitmap != null) {
                        // Add the image to the list
                        val filenam = item.path.replace("/", "")
                        fileList.add(DrawViewObject(count, filenam, bitmap))
                        count += 1
                    } else {
                        Log.e("DownloadImage", "Failed to download image")
                    }

                    // Once all images are downloaded, call the callback with the fileList
                    downloadedCount += 1
                    if (downloadedCount == totalItems) {
                        callback(fileList)
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            Log.e("DownloadError", "Failed to list files: $exception")
        }
}

}

// This factory class allows us to define custom constructors for the view model

class DrawViewModelFactory(private val repository: FileRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrawViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DrawViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}