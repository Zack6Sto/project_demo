package com.example.project_demo.view.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.project_demo.R
import com.example.project_demo.utils.Utils
import com.example.project_demo.utils.dialog.DialogPresenter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import qiu.niorgai.StatusBarCompat
import java.util.prefs.Preferences


abstract class BaseFragment : Fragment() {

//    val toolbarViewModel: ToolbarViewModel by viewModel()

    val mUtils: Utils by inject()

    val mPreferences: Preferences by inject()

//    val mCheckPermission: CheckPermission by inject { parametersOf(requireActivity()) }
//
    val mDialogPresenter: DialogPresenter by inject {  parametersOf(requireActivity()) }

//    val mTokenExpiredDisposable: TokenExpired by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // val view = inflater.inflate(layoutRes(), container, false)
        return view
    }

    fun onSetStatusBarColor(color: Int) {
        StatusBarCompat.setStatusBarColor(requireActivity(), ContextCompat.getColor(requireActivity(), color))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun onSetStatusBar() {
        StatusBarCompat.translucentStatusBar(requireActivity(), true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun onSetFullScreenStatusBar() {
        StatusBarCompat.translucentStatusBar(requireActivity())
        StatusBarCompat.translucentStatusBar(requireActivity(), true)
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
}