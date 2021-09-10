package com.example.project_demo.view.login

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityLoginBinding
import com.example.project_demo.utils.dialog.DialogUtil
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.view.main.MainActivity
import com.example.project_demo.view.register.RegisterActivity
import com.example.project_demo.view.termOfService.TermOfServiceActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.concurrent.schedule


class LoginActivity : BaseActivity() {

    private val viewModel : LoginViewModel by viewModel()

    private lateinit var binding : ActivityLoginBinding

//    private var auth: FirebaseAuth? = null
//
//    private  var firebaseUser: FirebaseUser? = null

    private var mAuth: FirebaseAuth? = null
    private val TAG: String = "LoginActivity"

    val alert = DialogUtil()

    private lateinit var navController: NavController

    private var cancellationSignal : CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("authentication error: $errString" )
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
//                    notifyUser("authentication success!!" )
//                    startActivity(Intent(this@LoginActivity,TermOfServiceActivity::class.java))
                    progressDialog("Login","รอสักครู่")
                    Timer().schedule(3000){
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                }
            }


    /**
     *checkForceUpdateApp START
     **/
    private var appUpdateManager: AppUpdateManager? = null
    private var installStateUpdatedListener: InstallStateUpdatedListener? = null
    private val FLEXIBLE_APP_UPDATE_REQ_CODE = 123
    /**
    *********************************************************************************
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        installStateUpdatedListener = InstallStateUpdatedListener { state: InstallState ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            } else if (state.installStatus() == InstallStatus.INSTALLED) {
                removeInstallStateUpdateListener()
            } else {
                Toast.makeText(
                    applicationContext,
                    "InstallStateUpdatedListener: state: " + state.installStatus(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        appUpdateManager!!.registerListener(installStateUpdatedListener!!)

        initView()
        initViewModel()
        initCheckSession()
        checkUpdate()
        checkBiometricSupport()
    }

    @SuppressLint("ResourceType")
    private fun initView() {
        hideKeyboardWhenTouch()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        onSetStatusBarColor(R.color.white)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
        onSubscriptOnClick()
    }

    private fun initCheckSession() {
        mAuth = FirebaseAuth.getInstance()
        if (mAuth!!.currentUser != null){
            Log.d(TAG,"Continue with:"+mAuth!!.currentUser!!.email)
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }

    private fun onSubscriptOnClick() {
        viewModel.mOnClickListener.observe(this, Observer {
            onStartAppIntent(it)
        })

//        binding.btnFingerprint.setOnClickListener {
//            Log.e("btnFingerprint","go to btnFingerprint")
//            //สแกนนิ้ว
//            fingerprint()
//        }
    }

    private fun onStartAppIntent(actionPage: String?) {
        when(actionPage){
            "intentLogin" -> {
                progressDialog("Login","รอสักครู่")
                Timer().schedule(3000){
//                    Log.e("progressDialog","go to TermOfServiceActivity")
//                    startActivity(Intent(applicationContext, TermOfServiceActivity::class.java))
                    performLogin()
                }
            }
            "intentRegister" -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
//
    //ดำเนินการเข้าสู่ระบบ
    private fun performLogin(){
        mAuth = FirebaseAuth.getInstance()
        val email = binding.edUser.text.toString().trim() {it <= ' '}
        val password = binding.edPass.text.toString().trim() {it <= ' '}
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "email and password are required",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (!it.isSuccessful) {
                        if (password.length < 6){
                            binding.edPass.error = "Please check your password. Password must have minimum 6 characters."
                            Log.d(TAG,"Authentication Failed 6 characters.")
                        }else{
                            Toast.makeText(applicationContext, "Authentication Failed:" + it.exception, Toast.LENGTH_SHORT).show()
                            Log.d(TAG,"Authentication Failed:" + it.exception)
                        }
                    } else {
                        Toast.makeText(applicationContext, "Sing in successfully!", Toast.LENGTH_SHORT).show()
                        Log.d(TAG,"Sing in successfully!")
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        }
    }
    /**
     สแกนลายนิ้วมือ
     **/
//    private fun fingerprint(){
//        //สแกนนิ้ว
//        val biometricPrompt = BiometricPrompt.Builder(this)
//            .setTitle("Title of AppDemo")
//            .setSubtitle("Authentication is required")
//            .setDescription("This app fingerprint protection to keep your data secure")
//            .setNegativeButton("Cancel",mainExecutor,DialogInterface.OnClickListener { dialog, which ->
//                notifyUser("Authentication cancelled")
//            }).build()
//
//        biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)
//    }

    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManager.isKeyguardSecure){
            notifyUser("fingerprint authentication has not been enabled in settings")
            return true
        }

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED){
            notifyUser("fingerprint authentication permission is not enabled")
            return true
        }

        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        }else true
    }

    private fun getCancellationSignal(): CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }


    //animations
    fun animations(v : View){
        YoYo.with(Techniques.Tada)
            .duration(700)
            .repeat(3)
            .playOn(v)
    }

    /**
    *checkForceUpdateApp START
     **/
    private fun checkUpdate() {
        val appUpdateInfoTask = appUpdateManager!!.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                startUpdateFlow(appUpdateInfo)
            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            }
        }
    }

    private fun startUpdateFlow(appUpdateInfo: AppUpdateInfo) {
        try {
            appUpdateManager!!.startUpdateFlowForResult(
                appUpdateInfo,
                AppUpdateType.FLEXIBLE,
                this,FLEXIBLE_APP_UPDATE_REQ_CODE
            )
        } catch (e: SendIntentException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FLEXIBLE_APP_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(
                    applicationContext,
                    "Update canceled by user! Result Code: $resultCode", Toast.LENGTH_LONG
                ).show()
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(
                    applicationContext,
                    "Update success! Result Code: $resultCode", Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Update Failed! Result Code: $resultCode",
                    Toast.LENGTH_LONG
                ).show()
                checkUpdate()
            }
        }
    }

    private fun popupSnackBarForCompleteUpdate() {
        Snackbar.make(
            findViewById<View>(android.R.id.content).rootView,
            "New app is ready!",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("Install") { view: View? ->
                if (appUpdateManager != null) {
                    appUpdateManager!!.completeUpdate()
                }
            }
            .setActionTextColor(resources.getColor(R.color.purple_200))
            .show()
    }

    private fun removeInstallStateUpdateListener() {
        if (appUpdateManager != null) {
            appUpdateManager!!.unregisterListener(installStateUpdatedListener!!)
        }
    }

    override fun onStop() {
        super.onStop()
        removeInstallStateUpdateListener()
    }
    /**
     *checkForceUpdateApp END
     **/
}