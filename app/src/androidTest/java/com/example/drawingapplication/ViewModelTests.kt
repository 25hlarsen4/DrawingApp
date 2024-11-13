//package com.example.drawingapplication
//
//
//import androidx.test.core.app.launchActivity
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.LargeTest
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class ViewModelTests {
//
//    private lateinit var viewModel: DrawViewModel
//
//    @get:Rule
//    var activityRule = ActivityScenarioRule(MainActivity::class.java)
//
//    @Before
//    fun setUp() {
//        launchActivity<MainActivity>().use { scenario ->
//            scenario.onActivity { activity ->
//                viewModel = activity.myViewModel
//            }
//        }
//    }
//
//    @Test
//    fun testPenSizeUpdate() {
//        val newSize = 10
//        viewModel.updatePenSize(newSize)
//        assertEquals(newSize, viewModel.strokeSize)
//    }
//
//    @Test
//    fun testPenColorUpdate() {
//        val newColor = 0xFFFF0000.toInt() // red color
//        viewModel.updateColor(newColor)
//        assertEquals(newColor, viewModel.colorVal)
//    }
//
//    @Test
//    fun testPenShapeUpdate() {
//        val shape2 = "circle"
//        viewModel.setShape(shape2)
//        // circle is true
//        assertTrue(viewModel.shape)
//
//        val shape1 = "square"
//        viewModel.setShape(shape1)
//        // square is false
//        assertFalse(viewModel.shape)
//    }
//
//    @Test
//    fun testGetColor() {
//        val newColor = 0xFFFF0000.toInt() // red color
//        viewModel.updateColor(newColor)
//        assertEquals(newColor, viewModel.getColor())
//    }
//}