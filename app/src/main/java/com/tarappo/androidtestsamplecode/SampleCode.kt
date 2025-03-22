package com.tarappo.androidtestsamplecode
import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun showShortToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
