package com.example.drawingapplication

import android.util.Log
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random

class FileRepository(private val scope: CoroutineScope,
                        private val dao: FileDAO) {

    //updated when the DB is modified
    val currentFile = dao.latestFile().asLiveData()

    //updated with the DB is modified
    val allFiles = dao.allFiles().asLiveData()

    fun addFile(filename: String){
        scope.launch {
            val file = FileData(filename, Date())
            dao.addFileData(file)
            Log.e("REPO", "told the DAO")
        }
    }
}