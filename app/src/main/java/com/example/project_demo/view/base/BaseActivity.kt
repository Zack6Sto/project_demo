package com.example.project_demo.view.base

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.project_demo.R
import com.example.project_demo.utils.KeyboardUtil
import com.example.project_demo.utils.Utils
import com.example.project_demo.utils.dialog.DialogPresenter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import qiu.niorgai.StatusBarCompat
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    val mUtils: Utils by inject()

    val mPreferences: com.example.project_demo.data.Preferences by inject()

    val toolbarViewModel: ToolbarViewModel by viewModel()

    //    val mCheckPermission: CheckPermission by inject { parametersOf(requireActivity()) }

    val mDialogPresenter: DialogPresenter by inject {  parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun progressDialog(title:String , message: String){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle(title)
        progressDialog.setMessage(message)
        progressDialog.show()
    }
//
     fun closeWaitingDialog() {
        val waitingDialog = ProgressDialog(this)
        if (waitingDialog != null) {
            runOnUiThread { waitingDialog.dismiss() }
        }
    }
//
//
//    fun showWaitTingDialog(Message: String?) {
//        showWaitTingDialog(Message, "Loading...")
//    }
//
//    open fun showWaitTingDialog(Message: String?, Title: String?) {
//        showWaitTingDialog(Message,null)
//    }

    fun onSetStatusBarColor(color: Int) {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun onSetStatusBar() {
        StatusBarCompat.translucentStatusBar(this, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun onSetFullScreenStatusBar() {
        StatusBarCompat.translucentStatusBar(this)
        StatusBarCompat.translucentStatusBar(this, true)
    }


    fun setTitleToolBar(textViewTitle: TextView, messageTitle: String) {
        textViewTitle.text = messageTitle
    }

    fun getRandomNumberString(): String {
        val rnd = Random()
        val number = rnd.nextInt(999999)
        Log.e("random", number.toString())
        return String.format("%06d", number)
    }

    /**
     * Hide KeyBoard
     */
    fun hideKeyboard() {
        KeyboardUtil.HideKeyboard(this)
    }

    fun hideKeyboardWhenTouch() {
        val v = this.window.decorView
        v.setOnClickListener { v -> KeyboardUtil.HideKeyboard(v) }
        v.setOnTouchListener { v, event ->
            KeyboardUtil.HideKeyboard(v)
            false
        }
    }

    /**
     * Show Keyboard
     */
    fun showKeyboard() {
        KeyboardUtil.ShowKeyboard(this)
    }
    /**
     ************************************************************************************
     **/

    fun startIntentAnimation(isStatus: Boolean) {
        mUtils.eventStartAnimationIntent(this, isStatus)
    }

    /**
     ******************************PopUpSnackBar*****************************************
     **/
    fun popupSnackBar(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("ตกลง") { view: View? ->

            }
            .setActionTextColor(resources.getColor(R.color.purple_200))
            .show()
    }
    /**
     ************************************************************************************
     **/
    fun notifyUser(message:String){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }
}