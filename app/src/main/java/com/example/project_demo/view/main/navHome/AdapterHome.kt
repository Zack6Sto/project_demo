package com.example.project_demo.view.main.navHome

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.project_demo.R
import com.example.project_demo.vo.models.User
import com.example.project_demo.databinding.UserRowNewMessageBinding
import com.example.project_demo.view.messages.ChatLogActivity
import com.squareup.picasso.Picasso

class AdapterHome (
    private var ct: Context,
    private var mList:ArrayList<User>,
    private var mOnClickListener: MutableLiveData<Int>
    ):RecyclerView.Adapter<AdapterHome.ViewHolder>(){

    class ViewHolder(internal var binding:UserRowNewMessageBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_row_new_message,
            parent,
            false
        ) as UserRowNewMessageBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        holder.binding.usernameTextviewNewMessage.text = user.username
        Picasso.get().load(user.profileImageUrl).into(holder.binding.imageviewNewMessage)

        holder.binding.lineView.visibility =
            if (position != mList.lastIndex) View.VISIBLE else View.INVISIBLE

        holder.binding.root.setOnClickListener {
            mOnClickListener.value = position
            val intent = Intent(ct,ChatLogActivity::class.java)
            intent.putExtra("uid", user.uid)
            intent.putExtra("username", user.username)
            Log.e("onBindViewHolder","onBindViewHolder uid : " + user.uid)
            ct.startActivity(intent)
        }
    }

    override fun getItemCount() = mList.size

}