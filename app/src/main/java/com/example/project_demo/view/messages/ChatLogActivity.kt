package com.example.project_demo.view.messages

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityChatLogBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.vo.RetrofitInstance
import com.example.project_demo.vo.models.Chat
import com.example.project_demo.vo.models.NotificationData
import com.example.project_demo.vo.models.PushNotification
import com.example.project_demo.vo.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.ArrayList

class ChatLogActivity : BaseActivity() {

    private lateinit var binding: ActivityChatLogBinding

    private val viewModel: ChatLogViewModel by viewModel()

    private var firebaseUser : FirebaseUser? = null

    private var reference: DatabaseReference? = null

    var chatList = ArrayList<Chat>()

    var topic = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_log)


        initView()
        initViewModel()
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
        binding.toolbarViewModel = toolbarViewModel
        onClickListener()
    }

    private fun initView() {

        binding.rcvChatLog.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val intent = intent
        val userId = intent.getStringExtra("uid")


        firebaseUser = FirebaseAuth.getInstance().currentUser

        reference = FirebaseDatabase.getInstance().getReference("users").child(userId!!)

        reference!!.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val user = snapshot.getValue(User::class.java)
                binding.tvUserName.text = user!!.username

                if (user.profileImageUrl == "") {
                    binding.imvProfile.setImageResource(R.drawable.account)
                } else {
                    Glide.with(this@ChatLogActivity).load(user.profileImageUrl).into(binding.imvProfile)
                }
                Log.e("ChatLogActivity","uid :" + user!!.uid)
                Log.e("ChatLogActivity","username :" + user.username)
                Log.e("ChatLogActivity","profileImageUrl :" + user.profileImageUrl)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun onClickListener() {

        val intent = intent
        val userId = intent.getStringExtra("uid")
        val userName = intent.getStringExtra("username")

        //ClickListener
        viewModel.mOnClickListener.observe(this, Observer {
            when(it){
                "send" ->{
                    val message: String = binding.edittextChatLog.text.toString()

                    if (message.isEmpty()) {
                        Toast.makeText(applicationContext, "message is empty", Toast.LENGTH_SHORT).show()
                        binding.edittextChatLog.setText("")
                    } else {
                        sendMessage(firebaseUser!!.uid, userId!!, message)
                        binding.edittextChatLog.setText("")
                        topic = "/topics/$userId"
                        PushNotification(NotificationData( userName!!,message),
                            topic).also {
                            sendNotification(it)
                        }
                    }
                }
                "intentBack" ->{
                    this.onBackPressed()
                }
            }
        })
        readMessage(firebaseUser!!.uid, userId!!)
    }

    private fun sendMessage(senderId: String, receiverId: String, message: String) {
        val reference: DatabaseReference = FirebaseDatabase.getInstance().reference

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["senderId"] = senderId
        hashMap["receiverId"] = receiverId
        hashMap["message"] = message

        reference.child("Chat").push().setValue(hashMap)

    }

    fun readMessage(senderId: String, receiverId: String) {
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Chat")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val chat = dataSnapShot.getValue(Chat::class.java)

                    if (chat!!.senderId == (senderId) && chat.receiverId == receiverId ||
                        chat.senderId == receiverId && chat.receiverId == senderId
                    ) {
                        chatList.add(chat)
                    }
                }

                val chatAdapter = ChatAdapter(this@ChatLogActivity, chatList)

                binding.rcvChatLog.adapter = chatAdapter
            }
        })
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d("TAG", "Response: ${Gson().toJson(response)}")
            } else {
                Log.e("TAG", response.errorBody()!!.string())
            }
        } catch(e: Exception) {
            Log.e("TAG", e.toString())
        }
    }

}