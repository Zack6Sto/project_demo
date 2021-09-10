package com.example.project_demo.view.main.navHome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase

class NavHomeViewModel constructor(generalUseCase: GeneralUseCase) : ViewModel()  {

    val mOnClickListener = MutableLiveData<String>()

    val mOnClickItemSetting = MutableLiveData<Int>()


//    fun onClickChangeRestaurant(){
//        mOnClickListener.value = "changeRestaurant"
//    }

//    fun onClickLogOut() {
//        mLogoutCall.call()
//    }

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