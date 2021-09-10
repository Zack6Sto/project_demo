//package com.example.project_demo.utils.dialog
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.DialogFragment
//import com.example.project_demo.R
//
//class MyCustomDialog: DialogFragment() {
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.ic_baseline_account_circle_24);
//        return inflater.inflate(R.layout.custom_dialog_fragment.xml, container, false)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
//        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
//        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
//    }
//
//}