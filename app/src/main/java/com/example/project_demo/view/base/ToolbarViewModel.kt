package com.example.project_demo.view.base

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolbarViewModel : ViewModel() {

    val onClickToolbar = MutableLiveData<String>()

    val titleToolbarView = ObservableField("")

    val mFavorite = ObservableField(false)

    val mStateViewFavorite = ObservableField(false)

    val amountCarts = ObservableField(0F)

    val netPrice = ObservableField(0F)

    fun onClickBack() {
        onClickToolbar.value = "intentBack"
    }

    fun onClickFavorites() {
        mFavorite.set(!mFavorite.get()!!)
        onClickToolbar.value = "intentFavorites"
    }
}
