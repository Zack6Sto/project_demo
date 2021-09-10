package com.example.project_demo.view.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_demo.R
import com.example.project_demo.adapter.CustomItemTouchHelperCallback
import com.example.project_demo.databinding.ActivityExampleBinding
import com.example.project_demo.vo.models.ExampleItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class ExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleBinding

    private val viewModel : ExampleViewModel by viewModel()

    private val exampleList = generateDummyList(500)

//    private val adapter = ExampleAdapter(exampleList,viewModel.isStatusChecked)

    private val adapter by lazy {
        ExampleAdapter(exampleList, viewModel.isStatusChecked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_example)


        initView()
        initViewModel()
    }

    private fun initView() {

        //ปุ่มเพิ่ม
        binding.btnInsertItem.setOnClickListener {
            insertItem()
            Log.e("button",": btnInsertItem")
        }

        //ปุ่มลบ
        binding.btnRemoveItem.setOnClickListener {
            if (viewModel.isStatusChecked.equals(true)){
                removeItem(0)
                Log.e("btnRemoveItem",": 1")
            }else{
                Log.e("btnRemoveItem",": 2")
            }
//            removeItem(0)
//            Log.e("button",": btnRemoveItem")
            initRecyclerView()
        }

    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        val callback = CustomItemTouchHelperCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
    }

    fun insertItem() {
        val index = Random.nextInt(8)
        val newItem = ExampleItem(
            R.drawable.ic_launcher_foreground,
            "New item at position $index",
            "Line 2",
            false
        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(index:Int) {
//        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_baseline_settings_24
                1 -> R.drawable.ic_channels
                else -> R.drawable.ic_baseline_account_circle_24
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2",true)
            list += item
        }
        return list
    }

}