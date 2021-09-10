package com.example.project_demo.view.main.navUsers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase
import com.example.project_demo.vo.models.User

class NavUsersViewModel (generalUseCase: GeneralUseCase) : ViewModel() {

    val mOnClickListener = MutableLiveData<String>()

    val mOnClickItemSetting = MutableLiveData<Int>()

    val getListUsersCall = MutableLiveData<Void>()

    val getListUsers = MutableLiveData<User>()

    val mResponseListUsersCall = Transformations.switchMap(getListUsersCall){
        generalUseCase.getListUsers()
    }
}