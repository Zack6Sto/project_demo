package com.example.project_demo.view.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase

class CalculatorViewModel(generalUseCase: GeneralUseCase):ViewModel() {

    val mOnClickListener = MutableLiveData<String>()

    fun onClickClear(){
        mOnClickListener.value = "clear"
    }

    fun onClickEqual(){
        mOnClickListener.value = "equal"
    }
}