import android.content.Context
import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.drawingapplication.DrawViewListScreen
import com.example.drawingapplication.DrawViewModel
import com.example.drawingapplication.FileDAO
import com.example.drawingapplication.FileDatabase
import com.example.drawingapplication.FileRepository
import com.example.drawingapplication.MainActivity
import com.example.drawingapplication.SharingScreen
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
class SharingListScreenTests {

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
    fun testSharingScreenStructure() {

        // Set up the Compose content for testing
        composeTestRule.setContent {
            SharingScreen(drawViewListViewModel = viewModel, navController = navController)
        }

        // Check if the button with the text "Create a new drawing" exists
        composeTestRule
            .onNodeWithText("Back")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Smile.png")
            .assertIsDisplayed()
    }
}