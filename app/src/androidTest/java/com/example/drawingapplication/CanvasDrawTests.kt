//package com.example.drawingapplication
//
//import android.content.pm.ActivityInfo
//import androidx.test.core.app.launchActivity
//import androidx.test.espresso.ViewAction
//import androidx.test.espresso.action.CoordinatesProvider
//import androidx.test.espresso.action.GeneralClickAction
//import androidx.test.espresso.action.Press
//import androidx.test.espresso.action.Tap
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.LargeTest
//import org.junit.Assert.assertEquals
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.matcher.ViewMatchers.withId
//
//
//fun clickIn(x: Int, y: Int): ViewAction {
//    return GeneralClickAction(
//        Tap.SINGLE,
//        CoordinatesProvider { view ->
//            val screenPos = IntArray(2)
//            view?.getLocationOnScreen(screenPos)
//
//            val screenX = (screenPos[0] + x).toFloat()
//            val screenY = (screenPos[1] + y).toFloat()
//
//            floatArrayOf(screenX, screenY)
//        },
//        Press.FINGER)
//}
//
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class CanvasDrawTests {
//
//    private lateinit var viewModel: DrawViewModel
//
//    @get:Rule
//    var activityRule = ActivityScenarioRule(MainActivity::class.java)
//
//    @Test
//    fun testDraw() {
//
//        launchActivity<MainActivity>().use { scenario ->
//            scenario.onActivity { activity ->
//                viewModel = activity.myViewModel
//            }
//
//            onView(withId(R.id.drawing_canvas)).perform(clickIn(400, 300))
//        }
//
//        assertEquals(400f, viewModel.startX, 10f)
//        assertEquals(300f, viewModel.startY, 10f)
//        assertEquals(400f, viewModel.endX, 10f)
//        assertEquals(300f, viewModel.endY, 10f)
//        assertEquals(true, viewModel.isPortrait)
//    }
//
//    @Test
//    fun testDrawScreenRotation() {
//        // Tests preservation of data
//        launchActivity<MainActivity>().use { scenario ->
//            scenario.onActivity { activity ->
//                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//                viewModel = activity.myViewModel
//
//            }
//            Thread.sleep(1000)
//            onView(withId(R.id.drawing_canvas)).perform(clickIn(550, 500))
//
//            assertEquals(550f, viewModel.startX, 10f)
//            assertEquals(500f, viewModel.startY, 10f)
//            assertEquals(550f, viewModel.endX, 10f)
//            assertEquals(500f, viewModel.endY, 10f)
//            assertEquals(false, viewModel.isPortrait)
//        }
//    }
//}