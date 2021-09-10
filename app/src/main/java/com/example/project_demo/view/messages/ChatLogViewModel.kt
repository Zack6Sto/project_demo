package com.example.project_demo.view.messages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase

class ChatLogViewModel(generalUseCase: GeneralUseCase): ViewModel() {

    val mOnClickListener = MutableLiveData<String>()

    fun onClickSendMessage(){
        mOnClickListener.value = "send"
    }

    fun onClickBack(){
        mOnClickListener.value = "intentBack"
    }

}