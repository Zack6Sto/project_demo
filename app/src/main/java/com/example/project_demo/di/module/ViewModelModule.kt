package com.example.project_demo.di.module

import com.example.project_demo.view.authPhoneNumber.AuthPhoneNumberViewModel
import com.example.project_demo.view.base.ToolbarViewModel
import com.example.project_demo.view.calculator.CalculatorViewModel
import com.example.project_demo.view.contact.ContactViewModel
import com.example.project_demo.view.example.ExampleViewModel
import com.example.project_demo.view.login.LoginViewModel
import com.example.project_demo.view.main.navHome.NavHomeViewModel
import com.example.project_demo.view.main.navSetting.NavSettingViewModel
import com.example.project_demo.view.main.navUsers.NavUsersViewModel
import com.example.project_demo.view.messages.ChatLogViewModel
import com.example.project_demo.view.otp.OtpViewModel
import com.example.project_demo.view.register.RegisterViewModel
import com.example.project_demo.view.termOfService.TermOfServiceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { ToolbarViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { OtpViewModel(get()) }
    viewModel { AuthPhoneNumberViewModel(get()) }
    viewModel { NavSettingViewModel(get()) }
    viewModel { NavHomeViewModel(get()) }
    viewModel { NavUsersViewModel(get()) }
    viewModel { ChatLogViewModel(get()) }
    viewModel { CalculatorViewModel(get()) }
    viewModel { ContactViewModel(get()) }
    viewModel { TermOfServiceViewModel(get()) }
    viewModel { ExampleViewModel(get()) }
}