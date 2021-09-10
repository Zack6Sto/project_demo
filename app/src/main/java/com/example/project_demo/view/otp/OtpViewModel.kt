package com.example.project_demo.view.otp

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase

class OtpViewModel(private val generalUseCase: GeneralUseCase): ViewModel(){
    val tvPhone = ObservableField("")
    val tvRef = ObservableField("123456")
    val etOtp = ObservableField("")
    val tvCountDown = ObservableField("04.59")
    val mOnClickListener = MutableLiveData<String>()
    private var mobile = "0861279543"


    fun onClickResend(){
        mOnClickListener.value = "resend"
    }

    fun onClickCancel(){
        mOnClickListener.value = "cancel"
    }

    fun onClickOK(){
        mOnClickListener.value = "ok"
    }


    fun setPhone() : String{
        mobile.length
        val phone = mobile.toCharArray()
        mobile = "" + phone[0] + phone[1] + phone[2] + "-XXX-X" + phone[7] + phone[8] + phone[9]
        return String.format(mobile)
    }
}