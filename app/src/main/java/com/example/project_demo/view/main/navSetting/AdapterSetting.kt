package com.example.project_demo.view.main.navSetting

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.project_demo.R
import com.example.project_demo.databinding.ItemSettingBinding
import com.example.project_demo.vo.ModelSetting

class AdapterSetting (
    private var ct:Context,
    private var mList:ArrayList<ModelSetting>,
    private var mOnClickListener:MutableLiveData<Int>
    ):RecyclerView.Adapter<AdapterSetting.ViewHolder>(){

    class ViewHolder(internal var binding:ItemSettingBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_setting,
            parent,
            false
        ) as ItemSettingBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = mList[position].title

        holder.binding.lineView.visibility =
            if (position != mList.lastIndex) View.VISIBLE else View.INVISIBLE

        holder.binding.root.setOnClickListener {
            mOnClickListener.value = position
        }
    }

    override fun getItemCount() = mList.size

}