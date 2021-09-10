package com.example.project_demo.view.termOfService

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.data.rest.repository.GeneralRepository

class TermOfServiceViewModel(generalRepository: GeneralRepository): ViewModel() {
    val isStatusButtonClick = ObservableField(false)
    val mOnClickListener =  MutableLiveData<String>()

    fun onClickOk(){
        mOnClickListener.value = "ok"
    }

    fun onCheckBox(){
        mOnClickListener.value = "checkBox"
    }
}