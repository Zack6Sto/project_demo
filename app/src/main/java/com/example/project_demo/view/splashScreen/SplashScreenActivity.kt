package com.example.project_demo.view.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivitySplashScreenBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.view.login.LoginActivity
import com.example.project_demo.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashScreenActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    private var mAuth: FirebaseAuth? = null
    private val TAG: String = "SplashScreenActivity"

    private var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initCheckSession()
    }

    private fun initView() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
    }

    private fun initCheckSession() {
        val intentApp: Intent

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null){
            Log.d(TAG,"Continue with:"+mAuth!!.currentUser!!.email)
            intentApp = Intent(this, MainActivity::class.java)
        }else{
            intentApp = Intent(this, LoginActivity::class.java)
        }

        mHandler.postDelayed({
            startActivity(intentApp)
            finishAffinity()
        }, 3000L)
    }
}