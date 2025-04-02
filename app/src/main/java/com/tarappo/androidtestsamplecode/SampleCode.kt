package com.tarappo.androidtestsamplecode
import android.content.Context
import android.widget.Toast
import androidx.core.content.edit

object ToastUtil {
    fun showShortToast(context: Context, message: String) {
        Toast.makeText(context, message + "Toast", Toast.LENGTH_SHORT).show()
    }
}

// SharedPreference用コード
class PreferenceUtil(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // 保存
    fun saveUserName(name: String) {
        prefs.edit() { putString("user_name", name) }
    }

    // 取得
    fun getUserName(): String? {
        return prefs.getString("user_name", null)
    }
}