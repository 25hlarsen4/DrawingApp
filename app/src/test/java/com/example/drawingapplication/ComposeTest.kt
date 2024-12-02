package com.example.drawingapplication

import android.content.Context
import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class UnitTests {

    @get:Rule
    val composeTestRule = createComposeRule()


    private lateinit var repository: FileRepository
    private lateinit var viewModel: DrawViewModel
    private lateinit var context: Context
    private lateinit var mockAuth: FirebaseAuth

    @Before
    fun setup() {
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
        context = ApplicationProvider.getApplicationContext()
        val repository: FileRepository = mock()

        // Initialize ViewModel with the mocked repository
        viewModel = DrawViewModel(repository, context)
//        val firestore = Firebase.firestore
//        firestore.useEmulator("10.0.2.2", 8080)
//
//        firestore.firestoreSettings = firestoreSettings {
//            isPersistenceEnabled = false
//        }

    }

    @Test
    fun testCreateNewDrawingButtonExists() {

        // Set up the Compose content for testing
        composeTestRule.setContent {
            DrawViewListScreen()
        }

        // Check if the button with the text "Create a new drawing" exists
        composeTestRule
            .onNodeWithText("Create a new drawing") // Find the button by text
            .assertIsDisplayed() // Ensure it is visible on the screen
    }
}