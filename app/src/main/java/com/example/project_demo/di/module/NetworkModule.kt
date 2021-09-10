package com.example.project_demo.di.module


import com.example.project_demo.data.rest.APIService
import com.example.project_demo.data.rest.OkHttpClientBuilder
import com.example.project_demo.data.rest.RetrofitBuilder
import org.koin.dsl.module

val networkModule = module {

    single { RetrofitBuilder }

    single<APIService> { get<RetrofitBuilder>().build(
        OkHttpClientBuilder.getUrlServer()) }
}
