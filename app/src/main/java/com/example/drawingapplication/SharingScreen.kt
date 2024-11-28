package com.example.drawingapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.ActivityScope
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.Date
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

@Composable
fun SharingScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val navController = (context as MainActivity).navController

    val drawViewListViewModel: DrawViewModel = (context as MainActivity).myViewModel

    Column(modifier = modifier.fillMaxSize()) {

        Button(
            onClick = {
                navController.navigate("drawingList")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Back")
        }


        // DrawViewList below the button
        DrawViewList(
            //change list to hold everything inside of fire database that isn't the users so
            // they can "share" just keep list if export is clicked
            list = drawViewListViewModel.DrawViewObjects,
            onSelectedTask = { drawViewObject ->
                //change behavior depending on which button was selected
                val baos = ByteArrayOutputStream()
                var bitmap = drawViewObject.bitmap
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos)
                drawViewListViewModel.uploadData(Firebase.storage.reference, drawViewObject.fileName, baos.toByteArray())
                navController.navigate("drawingList")
            },
            modifier = Modifier.weight(1f)
        )
    }
}

suspend fun downladImage(ref: StorageReference, path: String): Bitmap? {
    val fileRef = ref.child(path)
    return suspendCoroutine {
        fileRef.getBytes(10 * 1024 * 1024).addOnSuccessListener { bytes ->
            it.resume(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
        }.addOnFailureListener { e ->
            Log.e("DOWNLOAD_IMAGE", "Failed to get image $e")
            it.resume(null)
        }
    }
}

suspend fun downloadDocument(db: FirebaseFirestore): String {
    //probably bad to do this in a composable
    //grab a document from a public folder on firebase
    val collection = db.collection("demoCollection")
    return suspendCoroutine {
        collection
            .get()
            .addOnSuccessListener { result ->
                val doc = result.first()
                it.resume("${doc.id} => ${doc.data}")
            }
            .addOnFailureListener { exception ->
                Log.w("Uh oh", "Error getting documents.", exception)
                it.resume("No data")
            }
    }
}

suspend fun uploadDocument(id: String, document: Any) {
    val db = Firebase.firestore
    suspendCoroutine { continuation ->
        db.collection("users/").document(id)
            .set(document)
            .addOnSuccessListener {
                Log.e("UPLOAD", "SUCCESSFUL!")
                continuation.resume(Unit)
            }
            .addOnFailureListener {
                    e -> Log.e("UPLOAD", "FAILED!: $e")
                continuation.resume(Unit)
            }
    }
}


