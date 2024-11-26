package com.example.drawingapplication

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import com.example.drawingapplication.DrawViewModel
import com.example.drawingapplication.FileRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import android.os.Build
import org.junit.After
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.MockitoAnnotations



class UnitTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var repository: FileRepository
    private lateinit var viewModel: DrawViewModel
    private lateinit var context: Context

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(Build.FINGERPRINT).thenReturn("mocked_fingerprint")

        println(Build.FINGERPRINT) // Should print "mocked_fingerprint"

        context = mock()
        repository = mock()

        // Initialize ViewModel with the mocked repository
        viewModel = DrawViewModel(repository, context)
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