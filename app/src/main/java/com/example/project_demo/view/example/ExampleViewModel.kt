package com.example.project_demo.view.example

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_demo.domain.GeneralUseCase

class ExampleViewModel(generalUseCase: GeneralUseCase):ViewModel() {

    val isStatusChecked = ObservableField(true)

}