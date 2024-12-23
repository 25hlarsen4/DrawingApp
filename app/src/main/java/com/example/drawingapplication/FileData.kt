package com.example.drawingapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date


//apparently Room can't handle Date objects directly...
//Room will use these converters when going from DB <-> Kotlin
class TimeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}

//Defines a SQLITE table, basically
@Entity(tableName="filenames")
data class FileData(var filename: String,
                       var timestamp: Date){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 // integer primary key for the DB
}