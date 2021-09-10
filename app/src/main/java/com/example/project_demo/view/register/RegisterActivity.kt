package com.example.project_demo.view.register

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityRegisterBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.view.login.LoginActivity
import com.example.project_demo.view.main.MainActivity
import com.example.project_demo.vo.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.HashMap


class RegisterActivity : BaseActivity() {

    private  val viewModel: RegisterViewModel by viewModel()

    private lateinit var binding : ActivityRegisterBinding

//    private lateinit var auth: FirebaseAuth

    private var mAuth: FirebaseAuth? = null
    private val TAG: String = "LoginActivity"
    private lateinit var databaseReference: DatabaseReference
    private var firebaseUserID: String = ""

    //location
    val FINE_LOCATION_RQ = 101
    val CAMERA_RQ = 102


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        hideKeyboardWhenTouch()

        binding.btnGps.setOnClickListener {
            checkForPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION,"location",FINE_LOCATION_RQ)
        }

        binding.btnProfile.setOnClickListener {
            Log.d(TAG, "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
        onSubscriptOnClick()
    }

    private fun onSubscriptOnClick() {
    viewModel.mOnClickListener.observe(this, Observer {
        when(it){
            "profile"-> {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent,0)
            }
            "register"-> {

                val userName = binding.etUser.text.toString().trim() {it <= ' '}
                val fullName = binding.etFullName.text.toString().trim() {it <= ' '}
                val address = binding.etCompleteAddress.text.toString().trim() {it <= ' '}
                val email = binding.etEmail.text.toString().trim() {it <= ' '}
                val phone = binding.etPhone.text.toString().trim() {it <= ' '}
                val password = binding.etPassword.text.toString().trim() {it <= ' '}
                val confirmPassword = binding.etConfirmPassword.text.toString().trim() {it <= ' '}

                //เช็คว่าช่องไหนว่าง
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(applicationContext,"username is required",Toast.LENGTH_SHORT).show()
                }else if (TextUtils.isEmpty(fullName)){
                    Toast.makeText(applicationContext,"fullName is required",Toast.LENGTH_SHORT).show()
                }else if (TextUtils.isEmpty(address)){
                    Toast.makeText(applicationContext,"address is required",Toast.LENGTH_SHORT).show()
                }else if (TextUtils.isEmpty(email)){
                    Toast.makeText(applicationContext,"email is required",Toast.LENGTH_SHORT).show()
                }else if (TextUtils.isEmpty(phone)){
                    Toast.makeText(applicationContext,"phone is required",Toast.LENGTH_SHORT).show()
                }else if (TextUtils.isEmpty(password)){
                    Toast.makeText(applicationContext,"password is required",Toast.LENGTH_SHORT).show()
                }else if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(applicationContext,"confirm password is required",Toast.LENGTH_SHORT).show()
                }else if (password != confirmPassword){
                    Toast.makeText(applicationContext,"password not match",Toast.LENGTH_SHORT).show()
                }else{
                    performRegister(userName,email,password,fullName,address,phone)
                }
            }

            "login"-> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    })
    }

    //รูปภาพ

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // proceed and check what the selected image was....
            Log.d(TAG, "Photo was selected")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            binding.imvProfile.setImageBitmap(bitmap)

            binding.btnProfile.alpha = 0f

//      val bitmapDrawable = BitmapDrawable(bitmap)
//      selectphoto_button_register.setBackgroundDrawable(bitmapDrawable)
        }
    }

    //ดำเนินการสมัครสมาชิก
    private fun performRegister(userName:String,email:String,password:String,fullName: String,address: String,phone: String){
        mAuth = FirebaseAuth.getInstance()

        mAuth!!.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ it ->
                if (it.isSuccessful){
//                    firebaseUserID = mAuth!!.currentUser!!.uid
//                    databaseReference = FirebaseDatabase.getInstance().reference.child("users").child(firebaseUserID)
////
//                    val userHasMap = HashMap<String,Any>()
//                    userHasMap["uid"] = firebaseUserID
//                    userHasMap["username"] = userName
//                    userHasMap["fullname"] = fullName
//                    userHasMap["profileImageUrl"] = ""
//                    userHasMap["address"] = address
//                    userHasMap["email"] = email
//                    userHasMap["phone"] = phone
                Log.d(TAG,"Successfully created user with uid: ${it.result!!.user!!.uid}")
                    uploadImageToFirebaseStorage()

//                    databaseReference.updateChildren(userHasMap).addOnCompleteListener {
//                        if (it.isSuccessful){
//                            val intent = Intent(this, MainActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                    }

//                    databaseReference.setValue(hashMap).addOnCompleteListener(this){
//                        if (it.isSuccessful){
//                            //open home activity
//                            binding.etUser.setText("")
//                            binding.etEmail.setText("")
//                            binding.etPassword.setText("")
//                            binding.etConfirmPassword.setText("")
//                            val intent = Intent(this, MainActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                    }
                }else{
                    Toast.makeText(applicationContext, "Authentication Failed:" + it.exception, Toast.LENGTH_SHORT).show()
                }
//                if (!it.isSuccessful) {
//                    if (password.length < 6){
//                        binding.etPassword.error = "Please check your password. Password must have minimum 6 characters."
//                        Log.d(TAG,"Authentication Failed 6 characters.")
//                    }else{
//                        Toast.makeText(applicationContext, "Authentication Failed:" + it.exception, Toast.LENGTH_SHORT).show()
//                        Log.d(TAG,"Authentication Failed:" + it.exception)
//                    }
//                } else {
//                    Toast.makeText(applicationContext, "Create account successfully!", Toast.LENGTH_SHORT).show()
//                    Log.d(TAG,"Create account successfully!")
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
            }
            .addOnFailureListener {
              mDialogPresenter.showAlertDialog(this,it.message)
                Log.d(TAG,"Failed to created user : ${it.message}")
            }
    }


    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d(TAG, "File Location: $it")

                    saveUserToFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to upload image to storage: ${it.message}")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid?:""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val userName = binding.etUser.text.toString().trim() {it <= ' '}
        val fullName = binding.etFullName.text.toString().trim() {it <= ' '}
        val address = binding.etCompleteAddress.text.toString().trim() {it <= ' '}
        val phone = binding.etPhone.text.toString().trim() {it <= ' '}

        val user = User(uid,userName,fullName,profileImageUrl,address,phone)

        ref.setValue(user).addOnSuccessListener {
            Log.d(TAG,"Finally we saved the user to Firebase Database")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //*************

    private fun checkForPermissions(permission: String,name: String,requestCode: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(applicationContext,permission) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(applicationContext,"$name permission granted",Toast.LENGTH_LONG).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission,name,requestCode)

                else-> ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String){
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext,"$name permission refused",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"$name permission granted",Toast.LENGTH_LONG).show()
            }
        }

        when(requestCode){
            FINE_LOCATION_RQ -> innerCheck("location")
            CAMERA_RQ -> innerCheck("camera")
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("OK"){dialog ,which ->
                ActivityCompat.requestPermissions(this@RegisterActivity, arrayOf(permission),requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}