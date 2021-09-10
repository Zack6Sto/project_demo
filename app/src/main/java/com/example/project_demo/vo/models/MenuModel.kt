package com.example.project_demo.vo.models

class MenuModel {
    //public final int _Drawable;
    val _Drawable: String
    var _DrawableRight: String?

    //public int _DrawableRight;
    val _Label: String
    val _Class: String
    var _isFavouriteRow: Boolean

    constructor(
        DrawableLeft: String,
        label: String,
        Clazz: String,
        DrawableRight: String?,
        isFavouriteRow: Boolean
    ) {
        _Drawable = DrawableLeft
        _Label = label
        _Class = Clazz
        _DrawableRight = DrawableRight
        _isFavouriteRow = isFavouriteRow
    }

    constructor(DrawableLeft: String, label: String, Clazz: String, DrawableRight: String?) {
        _Drawable = DrawableLeft
        _Label = label
        _Class = Clazz
        _DrawableRight = DrawableRight
        _isFavouriteRow = false
    }

    constructor(DrawableLeft: String, label: String, Clazz: String) {
        _Drawable = DrawableLeft
        _Label = label
        _Class = Clazz
        _DrawableRight = null
        _isFavouriteRow = false
    }
}
