package com.example.project_demo.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CustomItemTouchHelperCallback(private var listener:CustomItemTouchHelperListener):ItemTouchHelper.Callback() {

    //มีไว้กำหนดทิศทางที่จะให้ Swipe หรือ Drag
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    //ทำงานเมื่อผู้ใช้ Drag & Drop ซึ่งจะรู้ได้ว่าผู้ใช้ลาก Item ไหนไปไว้ที่ตำแหน่งไหน
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
       if (viewHolder.itemViewType != target.itemViewType){
           return false
       }
        return listener.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
    }

    //ทำงานเมื่อผู้ใช้ Swipe To Dismiss ซึ่งสามารถกำหนดได้ว่าจะให้ Item นั้นๆทำงานอะไร
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemDismiss(viewHolder.adapterPosition)
    }

//    override fun isLongPressDragEnabled(): Boolean {
//        // กดค้างเพื่อ Drag & Drop ได้หรือไม่
//        return true
//    }
//
//    override fun isItemViewSwipeEnabled(): Boolean {
//        // Swipe To Dismiss ได้หรือไม่
//        return true
//    }
}