package com.example.project_demo.view.main.navSetting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase

class NavSettingViewModel constructor(generalUseCase: GeneralUseCase) : ViewModel()  {

    val mOnClickListener = MutableLiveData<String>()

    val mOnClickItemSetting = MutableLiveData<Int>()


    fun onClickSave(){
        mOnClickListener.value = "save"
    }

    fun onClickImageProfile(){
        mOnClickListener.value = "imageProfile"
    }

    fun onClickLogOut() {
       mOnClickListener.value = "logout"
    }

//    var mProFileCall = SingleLiveData<Void>()
//    val mResponseProfile = Transformations.switchMap(mProFileCall) {
//        generalUseCase.getProfile()
//    }
//
//    val mResponseLogout = Transformations
//        .switchMap(mLogoutCall) {
//            generalUseCase.doLogout()
//        }

}