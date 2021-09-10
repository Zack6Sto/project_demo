package com.example.project_demo.view.termOfService

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityTermOfServiceBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.view.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TermOfServiceActivity : BaseActivity() {

    private lateinit var binding : ActivityTermOfServiceBinding

    private val viewModel: TermOfServiceViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        onSetStatusBarColor(R.color.white)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_term_of_service)
    }


    private fun initViewModel() {
        binding.dataViewModel = viewModel

        initSetDataTerm()
        onClickListener()
    }

    @SuppressLint("ShowToast")
    private fun onClickListener() {
        viewModel.mOnClickListener.observe(this, Observer {
            when(it){
                "ok" ->{
//                    Toast.makeText(this, "บักลามก", Toast.LENGTH_LONG)
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
            }
        })

        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                Log.e("checkbox", ":$isChecked")
                viewModel.isStatusButtonClick.set(isChecked)
            }else{
                Log.e("checkbox", ":$isChecked")
                viewModel.isStatusButtonClick.set(isChecked)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initSetDataTerm() {
        binding.webViewTermOfService.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }

//        binding.webViewTermOfService.loadUrl("https://www.xnxx.com/")
        binding.webViewTermOfService.loadUrl("https://covid19.workpointnews.com/")
        binding.webViewTermOfService.settings.javaScriptEnabled = true
        binding.webViewTermOfService.settings.allowContentAccess = true
        binding.webViewTermOfService.settings.domStorageEnabled = true
        binding.webViewTermOfService.settings.useWideViewPort = true
        binding.webViewTermOfService.settings.setAppCacheEnabled(true)
    }


}