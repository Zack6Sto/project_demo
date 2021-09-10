package com.example.project_demo.view.example

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.project_demo.R
import com.example.project_demo.adapter.CustomItemTouchHelperListener
import com.example.project_demo.databinding.ExampleItemBinding
import com.example.project_demo.vo.models.ExampleItem
import java.util.*

class ExampleAdapter(private var exampleList: ArrayList<ExampleItem>,private var mIsCheckedStatus: ObservableField<Boolean>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>(),CustomItemTouchHelperListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.example_item,
            parent,false
        ) as ExampleItemBinding
        return ExampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.binding.imageView.setImageResource(currentItem.imageResource)
        holder.binding.textView1.text = currentItem.text1
        holder.binding.textView2.text = currentItem.text2
//        holder.checked.equals(currentItem.isChecked)
        holder.binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
//            mIsCheckedStatus.value = isChecked
            if (isChecked){
                Log.e("mIsCheckedStatus 1",isChecked.toString())
            }else{
                Log.e("mIsCheckedStatus 2",isChecked.toString())
            }
        }
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(internal var binding:ExampleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean {
        // เมื่อ Item ถูก Drag & Drop
        Collections.swap(exampleList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        // เมื่อ Item ถูก Swipe To Dismiss
        exampleList.removeAt(position)
        notifyItemRemoved(position)
    }
}