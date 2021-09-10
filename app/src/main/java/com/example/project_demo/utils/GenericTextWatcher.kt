package com.example.project_demo.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class GenericTextWatcher : TextWatcher {
    private val editText_one: EditText? = null
    private val editText_two: EditText? = null
    private val editText_three: EditText? = null
    private val editText_four: EditText? = null
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun afterTextChanged(editable: Editable) {
        run {
            if (editable.length == 1) {
                if (editText_one!!.length() == 1) {
                    editText_two!!.requestFocus()
                }
                if (editText_two!!.length() == 1) {
                    editText_three!!.requestFocus()
                }
                if (editText_three!!.length() == 1) {
                    editText_four!!.requestFocus()
                }
            } else if (editable.isEmpty()) {
                if (editText_four!!.length() == 0) {
                    editText_three!!.requestFocus()
                }
                if (editText_three!!.length() == 0) {
                    editText_two!!.requestFocus()
                }
                if (editText_two!!.length() == 0) {
                    editText_one!!.requestFocus()
                }
            }
        }
    }
}
