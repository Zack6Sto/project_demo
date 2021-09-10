package com.example.project_demo.view.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase


class ContactViewModel ( generalUseCase: GeneralUseCase):ViewModel() {

    val mOnClickListener = MutableLiveData<String>()

    val getContactUsCall = MutableLiveData<Void>()

    val mResponseContactsUs = Transformations.switchMap(getContactUsCall){
        generalUseCase.getContactsUs()
    }

    fun onClickLoginFaceBook(){
        mOnClickListener.value = "facebook"
    }

    fun onClickLoginLine(){
        mOnClickListener.value = "line"
    }

}