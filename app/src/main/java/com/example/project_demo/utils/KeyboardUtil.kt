package com.example.project_demo.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtil {
    fun HideKeyboard(Ac: Activity?) {
        if (Ac != null) {
            var v = Ac.currentFocus
            if (v == null) {
                val w = Ac.window
                v = w.currentFocus
                if (v == null) {
                    v = w.decorView
                }
                v.clearFocus()
            }
            HideKeyboard(v)
        }
    }

    fun ShowKeyboard(Ac: Activity?) {
        val v: View? = ActivityUtil.getCurrentViewFromActivity(Ac)
        if (v != null) {
            ShowKeyboard(v)
        }
    }

    fun HideKeyboard(v: View?) {
        if (v != null) {
            val imm = getKeyboardManager(v.context)
            if (imm != null) {
                val Token = v.windowToken
                if (!imm.hideSoftInputFromWindow(Token, 0)) {
                    imm.hideSoftInputFromInputMethod(Token, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        }
    }

    fun ShowKeyboard(v: View?) {
        if (v != null) {
            val imm = getKeyboardManager(v.context)
            if (imm != null) {
                if (!imm.showSoftInput(v, InputMethodManager.SHOW_FORCED)) {
                    val Token = v.windowToken
                    imm.showSoftInputFromInputMethod(Token, 0)
                }
            }
        }
    }

    fun getKeyboardManager(Ac: Activity): InputMethodManager? {
        val Obj = Ac.getSystemService(Context.INPUT_METHOD_SERVICE)
        return if (Obj != null) Obj as InputMethodManager else null
    }

    fun getKeyboardManager(Ac: Context): InputMethodManager? {
        val Obj = Ac.getSystemService(Context.INPUT_METHOD_SERVICE)
        return if (Obj != null) Obj as InputMethodManager else null
    }
}
