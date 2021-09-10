package com.example.project_demo.view.main.navUsers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_demo.R
import com.example.project_demo.databinding.ItemUserBinding
import com.example.project_demo.view.messages.ChatLogActivity
import com.example.project_demo.vo.models.User

class UserAdapter(
    private val context: Context,
    private val userList: ArrayList<User>,
    private var mOnClickListener: MutableLiveData<Int>
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(internal var binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
          LayoutInflater.from(parent.context),
          R.layout.item_user,parent,false
        ) as ItemUserBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.tvUserName.text = user.username
        Glide.with(context).load(user.profileImageUrl).placeholder(R.drawable.account).into(holder.binding.imvUserImage)

        holder.binding.root.setOnClickListener {
            mOnClickListener.value = position
            val intent = Intent(context, ChatLogActivity::class.java)
            intent.putExtra("uid",user.uid)
            intent.putExtra("username",user.username)
            context.startActivity(intent)
        }
    }

    fun deleteItem(index:Int){
        userList.removeAt(index)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}