package com.tarappo.androidtestsamplecode

import android.os.Build
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.printToString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.util.ReflectionHelpers

@RunWith(RobolectricTestRunner::class)
class RobolectricExtensionJUnit4Test {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        // FINGERPRINTを明示的に設定
        ReflectionHelpers.setStaticField(Build::class.java, "FINGERPRINT", "robolectric")
    }

    @Test
    fun testComposeComponent() {
        composeTestRule.setContent {
            Greeting("Android!")
        }

        composeTestRule.onNodeWithText("Hello Android!").assertExists()
    }
}

