package com.example.project_demo.view.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase
import com.example.project_demo.utils.TextHelper
import com.example.project_demo.utils.TextWatcherAdapter

class LoginViewModel(private val generalUseCase: GeneralUseCase) : ViewModel() {
    val etUser = ObservableField ("")
    val etPass = ObservableField ("")
    val mOnClickListener = MutableLiveData<String>()
    val isStatusButtonClick = ObservableField(false)

    fun onClickLogin(){
        mOnClickListener.value = "intentLogin"
    }

    fun onClickRegister(){
        mOnClickListener.value = "intentRegister"
    }

    val onUserNameTextChanged = TextWatcherAdapter { s ->
        etUser.set(s)
        checkEventButtonClick()
    }

    val onPasswordTextChanged = TextWatcherAdapter { s ->
        etPass.set(s)
        checkEventButtonClick()
    }

    private fun checkEventButtonClick() {
        isStatusButtonClick.set(TextHelper.isNotEmptyStrings(etUser.get()) && TextHelper.isNotEmptyStrings(etPass.get()))
    }
}