package com.example.project_demo.view.main.navUsers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_demo.R
import com.example.project_demo.databinding.FragmentUsersBinding
import com.example.project_demo.view.base.BaseFragment
import com.example.project_demo.view.main.navSetting.SettingFragment
import com.example.project_demo.vo.enumClass.Status
import com.example.project_demo.vo.firebase.FirebaseService
import com.example.project_demo.vo.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding

    private val viewModel: NavUsersViewModel by viewModel()

    var userList = ArrayList<User>()


    companion object {
        fun newInstance(loadPage: String? = ""): SettingFragment {
            val args = Bundle()
            args.putString("keyParam", loadPage)
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//         //สีstatusBar
//        requireActivity().window.statusBarColor = Color.parseColor("#00000000")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideSoftKeyboard(requireActivity())

        binding.dataViewModel = viewModel
        binding.rcvUsers.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstallations.getInstance().id.addOnSuccessListener {
            FirebaseService.token = it
            Log.e("FirebaseService","token :" + it)
        }

//        getUsersList()
//        onSubScriptUsersList()
    }

    private fun onSubScriptUsersList() {
        viewModel.getListUsersCall.value = null
        viewModel.mResponseListUsersCall.observe(requireActivity(), Observer {
            binding.loadResource = it
            when(it.status){
                Status.SUCCESS -> {
                    getUsersList()
                }
                Status.ERROR -> mDialogPresenter.dialogMessage(resources.getString(R.string.message_alert_dialog),it.message){}
            }
        })
    }

    fun getUsersList() {

        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        val userid = firebase.uid

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")

        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                val currentUser = snapshot.getValue(User::class.java)
//
//                if (currentUser!!.profileImageUrl == ""){
//                    binding.imvLogo.setImageResource(R.drawable.account)
//                }else{
//                    Glide.with(requireContext()).load(currentUser.profileImageUrl).into(binding.imvLogo)
//                }

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)

                    if (user!!.uid != firebase.uid) {

                        userList.add(user)
                    }
                }

                val userAdapter = UserAdapter(requireContext(), userList,viewModel.mOnClickItemSetting)

                binding.rcvUsers.adapter = userAdapter
            }

        })
    }

    //Class ContextWrapper
    fun getSharedPreferences(name: String?, mode: Int): SharedPreferences {
        return requireContext().getSharedPreferences(name, mode)
    }

    fun hideSoftKeyboard(activity: Activity) {
        if (activity.currentFocus == null){
            return
        }
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }
}
