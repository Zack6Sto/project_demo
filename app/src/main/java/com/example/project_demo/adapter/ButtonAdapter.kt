package com.example.project_demo.adapter

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import java.util.*

open class ButtonAdapter<T> : ArrayAdapter<T> {
    private var _notifyWhenChange = true

    constructor(context: Context?, resource: Int) : super(context!!, resource) {}
    constructor(
        context: Context?, textViewResourceId: Int,
        objects: List<T>?
    ) : super(
        context!!, textViewResourceId,
        objects!!
    ) {        // TODO Auto-generated constructor stub
    }

    private var ListenerTable: ArrayList<OnClickBackView>? = null
    fun ClearListener() {
        if (ListenerTable != null) {
            ListenerTable!!.clear()
        }
    }

    private fun InvokeListener(Position: Int, v: View) {
        if (ListenerTable != null) {
            for (Listener in ListenerTable!!) {
                Listener.OnClickButton(Position, v)
            }
        }
    }

    fun setOnClickBackView(Listener: OnClickBackView?) {
        if (Listener != null) {
            if (ListenerTable == null) {
                ListenerTable = ArrayList<OnClickBackView>()
            }
            ListenerTable!!.add(Listener)
        }
    }

    protected fun setOnClickButton(v: View?, position: Int) {
        v?.setOnClickListener { v -> InvokeListener(position, v) }
    }

    override fun setNotifyOnChange(notifyOnChange: Boolean) {
        _notifyWhenChange = notifyOnChange
        //        super.setNotifyOnChange(notifyOnChange);
    }

    override fun notifyDataSetChanged() {
        if (_notifyWhenChange) {
            super.notifyDataSetChanged()
        }
    }

    @Synchronized
    fun refresh() {
        super.notifyDataSetChanged()
    }
}
