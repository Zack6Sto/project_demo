package com.service.beneat.di.module

import androidx.fragment.app.FragmentActivity
import com.example.project_demo.data.Preferences
import com.example.project_demo.utils.Utils
import com.example.project_demo.utils.dialog.DialogPresenter
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilityModule = module {

    single { Preferences(androidApplication()) }

    factory { (activity: FragmentActivity) -> DialogPresenter(activity) }

//    single { MapUtils(androidApplication()) }

//    single { TokenExpired(androidApplication(),get()) }

    single { Utils(androidApplication(), get()) }

//    factory { (activity: AppCompatActivity) ->
//        CheckPermission(activity, ConvertUriToFile(androidApplication()))
//    }
//
//    single { ConvertUriToFile(androidApplication()) }
//
//    single { SaveImageView(androidApplication()) }
}


