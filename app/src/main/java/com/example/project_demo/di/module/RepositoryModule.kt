package com.example.project_demo.di.module

import com.example.project_demo.data.rest.repository.GeneralRepository
import com.example.project_demo.domain.GeneralUseCase
import org.koin.dsl.module

val repositoryModule = module {

    single { GeneralRepository(get()) }

    single { GeneralUseCase(get(), get()) }

//    single { MainRepository(get()) }
//
//    single { MainUseCase(get(), get()) }

}