package com.tarappo.androidtestsamplecode.junit5

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tarappo.androidtestsamplecode.ToastUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
@Config(application = RobolectricExtensionSelfTest.MyTestApplication::class)
class RobolectricExtensionSelfTest {
    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    @DisplayName("Robolectricサンプルテスト")
    fun shouldInitializeAndBindApplicationAndCallOnCreate() {
        val application = ApplicationProvider.getApplicationContext<Context>()
        assertInstanceOf(MyTestApplication::class.java, application)
        assertTrue((application as MyTestApplication).onCreateWasCalled)
    }

    @Test
    @DisplayName("Toastに対するテスト")
    fun toastTest() {
        val context = RuntimeEnvironment.getApplication()
        val expectedMessage = "Hello from Robolectric JUnit5!"

        ToastUtil.showShortToast(context, expectedMessage)

        // Toastによるメッセージ表示を仮想的にテストするためのもの
        val actualToast = ShadowToast.getTextOfLatestToast()
        assertEquals(expectedMessage, actualToast)
    }

    // サンプルコード
    class MyTestApplication : Application() {
        internal var onCreateWasCalled = false

        override fun onCreate() {
            this.onCreateWasCalled = true
        }
    }
}
