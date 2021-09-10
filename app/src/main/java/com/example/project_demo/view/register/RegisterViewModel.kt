package com.example.project_demo.view.register

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase
import com.example.project_demo.utils.TextHelper
import com.example.project_demo.utils.TextWatcherAdapter

class RegisterViewModel(private val generalUseCase: GeneralUseCase) : ViewModel() {
    val etUser = ObservableField ("")
    val etEmail = ObservableField ("")
    val etPass = ObservableField ("")
    val etConfirmPass = ObservableField ("")
    val mOnClickListener = MutableLiveData<String>()
    val isStatusButtonClick = ObservableField(false)

    fun onClickProfile(){
        mOnClickListener.value = "profile"
    }

    fun onClickRegister(){
        mOnClickListener.value = "register"
    }

    fun onClickLogin(){
        mOnClickListener.value = "login"
    }

    val onUserNameTextChanged = TextWatcherAdapter { s ->
        etUser.set(s)
        checkEventButtonClick()
    }

    val onEmailTextChanged = TextWatcherAdapter { s ->
        etEmail.set(s)
        checkEventButtonClick()
    }

    val onPasswordTextChanged = TextWatcherAdapter { s ->
        etPass.set(s)
        checkEventButtonClick()
    }

    val onConfirmPasswordTextChanged = TextWatcherAdapter { s ->
        etConfirmPass.set(s)
        checkEventButtonClick()
    }

    private fun checkEventButtonClick() {
        isStatusButtonClick.set(TextHelper.isNotEmptyStrings(etUser.get()) && TextHelper.isNotEmptyStrings(etEmail.get()) && TextHelper.isNotEmptyStrings(etPass.get()) && TextHelper.isNotEmptyStrings(etConfirmPass.get()) )
    }
}