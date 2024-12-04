package com.example.drawingapplication

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class UnitTests {

    @get:Rule
    val composeTestRule = createComposeRule()
      private lateinit var activity: MainActivity

      private lateinit var repository: FileRepository
      private lateinit var viewModel: DrawViewModel
      private lateinit var context: Context
      private lateinit var mockAuth: FirebaseAuth
      private lateinit var db: FileDatabase
      private lateinit var dao: FileDAO
    @Before
    fun setup() {
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())

        context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, FileDatabase::class.java).build()

        dao = db.fileDao()

        repository = FileRepository(CoroutineScope(SupervisorJob() + Dispatchers.IO), dao)

        viewModel = DrawViewModel(repository, context)

    }

    @Test
    fun testCreateNewDrawingButtonExists() {

        // Set up the Compose content for testing
        composeTestRule.setContent {
            DrawViewListScreen(drawViewListViewModel = viewModel)
        }

        // Check if the button with the text "Create a new drawing" exists
        composeTestRule
            .onNodeWithText("Download Image") // Find the button by text
            .assertIsDisplayed() // Ensure it is visible on the screen
    }
}