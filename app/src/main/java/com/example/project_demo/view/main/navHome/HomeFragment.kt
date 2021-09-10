package com.example.project_demo.view.main.navHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.project_demo.R
import com.example.project_demo.vo.models.User
import com.example.project_demo.databinding.FragmentHomeBinding
import com.example.project_demo.view.base.BaseFragment
import com.example.project_demo.view.main.navSetting.SettingFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: NavHomeViewModel by viewModel()

    private lateinit var dbRef : DatabaseReference

    var firedatabase : FirebaseDatabase? = null

    private var mDataUser = ArrayList<User>()

//    private val mAdapterHome by lazy {
//        AdapterHome(requireContext(),mDataUser,viewModel.mOnClickItemSetting)
//    }

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
        //สีstatusBar
//        requireActivity().window.statusBarColor = Color.parseColor("#FFBB86FC")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.dataViewModel = viewModel

        initView()
//        initViewModel()
//        onClickListener()
    }

    private fun initView() {
        getUserCurrent()
        popupSnackBar(binding.root,"ขอบคุณที่ทดสอบแอพของฉัน")
    }

    fun getUserCurrent() {

        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        val userid = firebase.uid

        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users/$userid")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val currentUser = snapshot.getValue(User::class.java)
                if (currentUser!!.profileImageUrl == ""){
                    binding.imvLogo.setImageResource(R.drawable.account)
                }else{
                    Picasso.get().load(currentUser.profileImageUrl).into(binding.imvLogo)
                }

            }

        })
    }
}