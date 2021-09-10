//package com.example.project_demo.view.authPhoneNumber
//
//import android.app.ProgressDialog
//import android.content.Context
//import android.content.Intent
//import android.opengl.Visibility
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.os.Handler
//import android.text.TextUtils
//import android.util.Log
//import android.view.View
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.Observer
//import com.example.project_demo.R
//import com.example.project_demo.databinding.ActivityAuthPhoneNumberBinding
//import com.example.project_demo.view.base.BaseActivity
//import com.example.project_demo.view.login.LoginActivity
//import com.example.project_demo.view.main.MainActivity
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import org.koin.androidx.viewmodel.ext.android.viewModel
//import java.util.concurrent.TimeUnit
//
//class AuthPhoneNumberActivity : BaseActivity() {
//
//    private val viewModel : AuthPhoneNumberViewModel by viewModel()
//
//    private lateinit var binding : ActivityAuthPhoneNumberBinding
////    private var mCountDownTimer: CountDownTimer? = null
////    private var mHandler = Handler()
//
//    //if code sending failed, will used to resend
//    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null
//
//    private var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
//    private var mVerificationId: String? = null
//    private lateinit var firebaseAuth: FirebaseAuth
//
//    private val TAG ="MAIN_TAG"
//
//    //progress dialog
////    private lateinit var progressDialog:ProgressDialog
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initView()
//        initViewModel()
//    }
//
//    private fun initView() {
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_auth_phone_number)
//        hideKeyboardWhenTouch()
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
//        binding.tvPhone.visibility = View.GONE
//        binding.etPinEntry.visibility = View.GONE
//        binding.txtResend.visibility = View.GONE
//        binding.tvResend.visibility = View.GONE
//        binding.btnCancel.visibility = View.GONE
//        binding.btnOk.visibility = View.GONE
//    }
//
//    private fun showPageOtp(){
////        binding.etPinEntry.visibility = View.GONE
////        binding.txtResend.visibility = View.GONE
////        binding.tvResend.visibility = View.GONE
////        binding.btnCancel.visibility = View.GONE
////        binding.btnOk.visibility = View.GONE
//
////        binding.text1.text = "Verification Code"
////        binding.text2.text = "Please type the verification code we sent to"
////        binding.tvPhone.text = phone
////        binding.edPhone.visibility = View.GONE
////        binding.btnPhoneContinue.visibility = View.GONE
//    }
//
//
//    private fun initViewModel() {
//        binding.dataViewModel = viewModel
//        onSubscriptOnClick()
//        initViewAuthPhoneNumber()
//    }
//
//    private fun onSubscriptOnClick() {
//        viewModel.mOnClickListener.observe(this, Observer {
//            when(it){
//                "resend" ->{
//                    Log.e("mOnClickListener","1 :"+ it.toString())
//                    //input phone number
//                    val phone = binding.edPhone.text.toString().trim()
//
//                    //validate phone number
//                    if (TextUtils.isEmpty(phone)){
//                        Toast.makeText(this@AuthPhoneNumberActivity,"Please enter phone number",Toast.LENGTH_SHORT).show()
//                    }else{
//                        binding.etPinEntry.text!!.clear()
//                        resendVerification(phone, forceResendingToken!!)
//                    }
//                }
//                "cancel" ->{
//                    Log.e("mOnClickListener","2 :"+ it.toString())
//                    startActivity(Intent(this,LoginActivity::class.java))
//                }
//                "ok" ->{
//                    Log.e("mOnClickListener","3 :"+ it.toString())
//                    //input  verification code
//                    val code = binding.etPinEntry.text.toString().trim()
//
//                    if (TextUtils.isEmpty(code)){
//                        Toast.makeText(this@AuthPhoneNumberActivity,"Please enter verification code",Toast.LENGTH_SHORT).show()
//                    }else{
//                        verifyPhoneNumberWithCode(mVerificationId.toString(),code)
//                    }
//                    hideKeyboard()
//                }
//                "PhoneContinue" ->{
//                    Log.e("mOnClickListener","4 :"+ it.toString())
//                    val phone = binding.edPhone.text.toString().trim()
//                    if (TextUtils.isEmpty(phone)){
//                        Log.e("ttttt", "phone if  : $phone")
//                        Toast.makeText(this@AuthPhoneNumberActivity,"Please enter phone number",Toast.LENGTH_SHORT).show()
//                    }else{
//                        Log.e("ttttt", "phone  else: $phone")
//                        Toast.makeText(this@AuthPhoneNumberActivity,phone,Toast.LENGTH_SHORT).show()
//                        startPhoneNumberVerification(phone)
//                    }
//                    hideKeyboard()
//                }
//            }
//        })
//    }
//
//    private fun initViewAuthPhoneNumber() {
//        firebaseAuth = FirebaseAuth.getInstance()
//        progressDialog = ProgressDialog(this)
//        progressDialog.setTitle("Please wail")
//        progressDialog.setCanceledOnTouchOutside(false)
//
//        mCallBacks = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//            override fun onVerificationCompleted(phoneAuthcredential: PhoneAuthCredential) {
//               signInWithPhoneAuthCredential(phoneAuthcredential)
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                progressDialog.dismiss()
//                Toast.makeText(this@AuthPhoneNumberActivity,"${e.message}",Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
//                Log.d(TAG,"onodeSent: $verificationId")
//                mVerificationId = verificationId
//                forceResendingToken = token
//                progressDialog.dismiss()
//
//                //hide phone layout, show code layout
//                binding.text1.text = "Verification Code"
//                binding.text2.text = "Please type the verification code we sent to"
//                binding.tvPhone.text = binding.edPhone.text.toString()
//
//                binding.tvPhone.visibility = View.VISIBLE
//                binding.etPinEntry.visibility = View.VISIBLE
//                binding.txtResend.visibility = View.VISIBLE
//                binding.tvResend.visibility = View.VISIBLE
//                binding.btnCancel.visibility = View.VISIBLE
//                binding.btnOk.visibility = View.VISIBLE
//
//                binding.edPhone.visibility = View.GONE
//                binding.btnPhoneContinue.visibility = View.GONE
////                super.onCodeSent(verificationId, token)
//            }
//
//        }
//    }
//
//    private fun startPhoneNumberVerification(phone: String){
//        progressDialog.setMessage("Verifying Phone Number...")
//        progressDialog.show()
//
//        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
//            .setPhoneNumber(phone)
//            .setTimeout(60L,TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(mCallBacks!!)
//            .build()
//
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }
//
//    private fun resendVerification(phone: String, token: PhoneAuthProvider.ForceResendingToken){
//        progressDialog.setMessage("Resending Code...")
//        progressDialog.show()
//
//        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
//            .setPhoneNumber(phone)
//            .setTimeout(60L,TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(mCallBacks!!)
//            .setForceResendingToken(token)
//            .build()
//
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }
//
//    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String){
//        progressDialog.setMessage("Verifying Code...")
//        progressDialog.show()
//
//        val credential = PhoneAuthProvider.getCredential(verificationId.toString(),code)
//        signInWithPhoneAuthCredential(credential)
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        progressDialog.setMessage("Logging In")
//
//        firebaseAuth.signInWithCredential(credential)
//            .addOnSuccessListener {
//                //login success
//                progressDialog.dismiss()
//                val phone = firebaseAuth.currentUser?.phoneNumber
//                Toast.makeText(this,"Logged In as $phone",Toast.LENGTH_SHORT).show()
//
//                //start profile activity
//                 startActivity(Intent(this,MainActivity::class.java))
//                finish()
//            }
//            .addOnFailureListener { e->
//
//                //login failed
//                progressDialog.dismiss()
//                Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
//            }
//    }
//
//}