import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCreateNewDrawingButtonExists() {
        // Set up the Compose content for testing
        composeTestRule.setContent {
            MainScreen(onCreateNewDrawing = {})
        }

        // Check if the button with the text "Create a new drawing" exists
        composeTestRule
            .onNodeWithText("Create a new drawing") // Find the button by text
            .assertIsDisplayed() // Ensure it is visible on the screen
    }
}
