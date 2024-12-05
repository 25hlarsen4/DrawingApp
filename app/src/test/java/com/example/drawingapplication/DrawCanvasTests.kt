package com.example.drawingapplication

import DrawCanvas
import android.content.Context
import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class DrawCanvasTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var repository: FileRepository
    private lateinit var viewModel: DrawViewModel
    private lateinit var context: Context
    private lateinit var mockAuth: FirebaseAuth
    private lateinit var db: FileDatabase
    private lateinit var dao: FileDAO
    private lateinit var navController: NavHostController
    @Before
    fun setup() {
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())

        context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, FileDatabase::class.java).build()

        dao = db.fileDao()

        repository = FileRepository(CoroutineScope(SupervisorJob() + Dispatchers.IO), dao)

        viewModel = DrawViewModel(repository, context)

        repository.addFile("Test.png")
        repository.addFile("Smile.png")

        navController = NavHostController(context)

    }

    @Test
    fun testDrawCanvasStructure() {

        // Set up the Compose content for testing
        composeTestRule.setContent {
            DrawCanvas(viewModel, navController)
        }

        // Check if the button with the text "Create a new drawing" exists
        composeTestRule
            .onNodeWithText("Shape")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Color")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Size")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Back")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Save")
            .assertIsDisplayed()
    }
}