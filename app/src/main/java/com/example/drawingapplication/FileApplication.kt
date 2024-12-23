package com.example.drawingapplication

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FileApplication : Application() {

    //coroutine scope tied to the application lifetime which we can run suspend functions in
    val scope = CoroutineScope(SupervisorJob())

    //get a reference to the DB singleton
    val db by lazy {Room.databaseBuilder(
        applicationContext,
        FileDatabase::class.java,
        "weather_database"
    ).build()}


    //create our repository using lazy to access the DB when we need it
    val fileRepository by lazy {FileRepository(scope, db.fileDao())}
}