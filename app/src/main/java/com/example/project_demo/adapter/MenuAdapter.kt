package com.example.project_demo.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project_demo.R
import com.example.project_demo.vo.models.MenuModel

class MenuAdapter : ButtonAdapter<MenuModel?> {
    private val dragDrawable: String = context.getResources().getResourceEntryName(R.drawable.ic_list)
    private var menuModelList: ArrayList<MenuModel>? = null

    constructor(context: Context?, resource: Int) : super(context, resource) {}
    constructor(
        context: Context?, textViewResourceId: Int,
        objects: ArrayList<MenuModel>?
    ) : super(context, textViewResourceId, objects) {
        menuModelList = objects
    }

    fun getMenuModelList(): ArrayList<MenuModel>? {
        return menuModelList
    }

    internal fun getView(position: Int, convertView: View, parent: ViewGroup):View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                R.layout.listview_text_row, parent, false
            )
        }
        val model: MenuModel? = getItem(position)
        val label = convertView!!.findViewById<View>(R.id.listview_row) as TextView
        val rightImage = convertView.findViewById<View>(R.id.listview_right_image) as ImageView
        if (model != null) {
            val resource: Resources = context.resources
            label.setCompoundDrawablesWithIntrinsicBounds(
                resource.getIdentifier(
                    model._Drawable,
                    "drawable",
                    context.packageName
                ), 0, 0, 0
            )
            label.text = model._Label
            if (model._DrawableRight != null) {
                rightImage.setImageResource(
                    resource.getIdentifier(
                        model._DrawableRight,
                        "drawable",
                        context.packageName
                    )
                )
                if (rightImage.drawable != null && !model._DrawableRight.equals(dragDrawable)) {
                    setOnClickButton(rightImage, position)
                }
            }
            if (model._isFavouriteRow) {
                rightImage.isSelected = true
            } else {
                rightImage.isSelected = false
            }
        } else {
            label.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            label.text = ""
        }
        return convertView
    }
}
