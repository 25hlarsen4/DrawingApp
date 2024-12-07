package com.example.drawingapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@RunWith(AndroidJUnit4::class)
class FirebaseTests {

    companion object {
        private lateinit var appContext: Context
        @BeforeClass
        @JvmStatic
        fun setup() {
            //10.0.2.2 on the device emulator means your laptop

            val userEmail = "testUser@gmail.com"
            val userPassword = "password12345"

            //make sure we have an account and we're logged int

            runBlocking {
                suspendCoroutine<Unit> { continuation ->
                    Firebase.auth.createUserWithEmailAndPassword(userEmail, userPassword)
                        .addOnSuccessListener {
                            Log.d("scs", "testAuth: ")
                            continuation.resume(Unit)
                        }
                        .addOnFailureListener { err ->
                            Log.d("test", "ignoring duplicate account erro ${err}")
                            continuation.resume(Unit)
                        }
                }
            }
            runBlocking {
                suspendCoroutine<Unit> { continuation ->
                    Log.d("login", "testAuth: logging in")
                    Firebase.auth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnSuccessListener {
                            Log.d("suser", "testAuth: ")
                            continuation.resume(Unit)
                        }
                        .addOnFailureListener { err ->
                            throw Exception(err)
                        }
                }
            }
        }
    }

    @Test
    fun test_image_upload_and_download() {
        Log.d("USER", "User: ${Firebase.auth.currentUser!!.email} ${Firebase.auth.currentUser!!.uid}")
        val path = "${Firebase.auth.currentUser!!.uid}/test.png"

        runBlocking {
            val baos = ByteArrayOutputStream()
            val data = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).compress(Bitmap.CompressFormat.PNG, 0, baos)
            val picture = baos.toByteArray()
            val fileRef = Firebase.storage.reference.child(path)
            fileRef.putBytes(baos.toByteArray())

            Log.d("upload", "done, start download")

            val downloadedData = suspendCoroutine {
                fileRef.getBytes(10 * 1024 * 1024).addOnSuccessListener { bytes ->
                    it.resume(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
                }.addOnFailureListener { e ->
                    Log.e("DOWNLOAD_IMAGE", "Failed to get image $e")
                    it.resume(null)
                }
            }

            //save it into PNG format (in memory, not a file)
            if (downloadedData != null) {
                downloadedData.compress(Bitmap.CompressFormat.PNG, 0, baos)
            }
            val downloadedBytes = baos.toByteArray() //bytes of the PNG
            assertArrayEquals(picture, downloadedBytes)
        }

    }
}