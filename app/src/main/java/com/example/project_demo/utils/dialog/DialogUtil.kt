package com.example.project_demo.utils.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.project_demo.R
import com.example.project_demo.databinding.DialogTwoButtonBinding
import java.util.*
import kotlin.collections.ArrayList

class DialogUtil {

    lateinit var context: Context

        fun showAlertDialog(activity: Activity?, msg: String?) {
            val dialog = Dialog(activity!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_layout)
            val text = dialog.findViewById<View>(R.id.text_dialog) as TextView
            text.text = msg
            val dialogButton: Button = dialog.findViewById<View>(R.id.btn_dialog) as Button
            dialogButton.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

    //----------------

//    fun withItems(view: View) {
//
//        val items = arrayOf("Red", "Orange", "Yellow", "Blue")
//        val builder = AlertDialog.Builder(this)
//        with(builder)
//        {
//            setTitle("List of Items")
//            setItems(items) { dialog, which ->
//                Toast.makeText(context, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
//            }
//
//            setPositiveButton("OK", positiveButtonClick)
//            show()
//        }
//    }

    fun withMultiChoiceList(view: View) {

        val items = arrayOf("Microsoft", "Apple", "Amazon", "Google")
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(context)

        builder.setTitle("This is list choice dialog box")
        builder.setMultiChoiceItems(items, null
        ) { dialog, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }

        builder.setPositiveButton("DONE") { dialogInterface, i ->
            val selectedStrings = ArrayList<String>()

            for (j in selectedList.indices) {
                selectedStrings.add(items[selectedList[j]])
            }

            Toast.makeText(context, "Items selected are: " + Arrays.toString(selectedStrings.toTypedArray()), Toast.LENGTH_SHORT).show()
        }

        builder.show()

    }

//    fun withStyle(view: View) {
//
//        val builder = AlertDialog.Builder(ContextThemeWrapper(this, android.R.style.Holo_SegmentedButton))
//
//        with(builder)
//        {
//            setTitle("Androidly Alert")
//            setMessage("We have a message")
//            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
//            setNegativeButton(android.R.string.no, negativeButtonClick)
//            setNeutralButton("Maybe", neutralButtonClick)
//            show()
//        }
//    }

//    fun withCustomStyle(view: View) {
//
//        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
//
//        with(builder)
//        {
//            setTitle("Androidly Alert")
//            setMessage("We have a message")
//            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
//            setNegativeButton(android.R.string.no, negativeButtonClick)
//            setNeutralButton("Maybe", neutralButtonClick)
//            show()
//        }
//
//    }

    fun withButtonCentered(view: View) {

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle("Title")
        alertDialog.setMessage("Message")

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes"
        ) { dialog, which -> dialog.dismiss() }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()

        val btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)

        val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 10f
        btnPositive.layoutParams = layoutParams
        btnNegative.layoutParams = layoutParams
    }

//    fun withEditText(view: View) {
//        val builder = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        builder.setTitle("With EditText")
//        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
//        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
//        builder.setView(dialogLayout)
//        builder.setPositiveButton("OK") { dialogInterface, i -> Toast.makeText(applicationContext, "EditText is " + editText.text.toString(), Toast.LENGTH_SHORT).show() }
//        builder.show()
//    }

    // When User cilcks on dialog button, call this method
    fun onAlertDialog(view: View) {
        //Instantiate builder variable
        val builder = AlertDialog.Builder(view.context)

        // set title
        builder.setTitle("New Update found")

        //set content area
        builder.setMessage("Update your android 9.0 to 10.0")

        //set negative button
        builder.setPositiveButton(
            "Update Now") { dialog, id ->
            // User clicked Update Now button
            Toast.makeText(context, "Updating your device",Toast.LENGTH_SHORT).show()
        }

        //set positive button
        builder.setNegativeButton(
            "Cancel") { dialog, id ->
            // User cancelled the dialog
        }

        //set neutral button
        builder.setNeutralButton("Reminder me latter") {dialog, id->
            // User Click on reminder me latter
        }
        builder.show()
    }


}