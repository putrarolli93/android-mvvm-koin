package com.example.testapp.utils.di

import com.example.testapp.network.ProvinsiRepository
import com.example.testapp.viewmodel.ProvinsiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ProvinsiViewModel(get())
    }

    single {
        ProvinsiRepository(get())
    }

}