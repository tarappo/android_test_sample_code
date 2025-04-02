package com.tarappo.androidtestsamplecode.junit4

import android.app.Application
import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tarappo.androidtestsamplecode.PreferenceUtil
import com.tarappo.androidtestsamplecode.ToastUtil
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
@Config(application = RobolectricExtensionSelfTest.MyTestApplication::class)
class RobolectricExtensionSelfTest {
    private lateinit var context: Context
    private lateinit var preferenceUtil: PreferenceUtil

    @Before
    fun setUp() {
        context = RuntimeEnvironment.getApplication()
        preferenceUtil = PreferenceUtil(context)
    }

    @Test
    fun shouldInitializeAndBindApplicationAndCallOnCreate() {
        // Robolectricの環境でApplicationインスタンスを取得
        val application = RuntimeEnvironment.getApplication() as MyTestApplication

        // onCreateが呼ばれているか確認する
        assertTrue(application.onCreateWasCalled)
    }

    @Test
    fun showShortToast_displaysCorrectMessage() {
        val context = RuntimeEnvironment.getApplication()
        val message = "Hello from Robolectric JUnit4"

        ToastUtil.showShortToast(context, message)

        // Toastによるメッセージ表示を仮想的にテストするためのもの
        val actualToast = ShadowToast.getTextOfLatestToast()
        Assertions.assertEquals(message + "Toast", actualToast)
    }

    @Test
    fun saveUserName_shouldStoreCorrectValue() {
        preferenceUtil.saveUserName("サンプル太郎")

        val savedName = preferenceUtil.getUserName()
        assertEquals("サンプル太郎", savedName)
    }

    @Test
    fun getUserName_shouldReturnNullIfNotSet() {
        val savedName = preferenceUtil.getUserName()
        assertNull(savedName)
    }

    // サンプルコード
    class MyTestApplication : Application() {
        internal var onCreateWasCalled = false

        override fun onCreate() {
            this.onCreateWasCalled = true
        }
    }
}
